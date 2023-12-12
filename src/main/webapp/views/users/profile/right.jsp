<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<ul>
	<c:forEach items="${PostsList}" var="Post" varStatus="st">
		<div class="container bootstrap snippets bootdey">
			<div class="col-md-8">
				<div class="box box-widget">
					<div class="box-header with-border">
						<div class="user-block" style="display: block;">
							<img class="img-circle" src="${Post.getUser().getAvatarLink() }">
							<span class="username"></span><a
								href="profile?profileID=${Post.getUser().getUserID() }">${Post.getUser().getUserName() }</a>
							<span class="description">${Post.getPostDate()}</span>
						</div>
					</div>

					<div class="box-body" style="display: block;">
						<p>${Post.getContent() }</p>
						<c:forEach items="${Post.getListImageLink() }" var="link">
							<img class="img-responsive pad" src="${link}">
						</c:forEach>
						<div style="padding-bottom: 32px">
							<button style="float: left" type="button"
								id="like-button-${Post.getPostID()}"
								class="btn btn-default btn-xs">
								<i class="fa fa-thumbs-o-up"></i>
								${listLike.get(st.index).getLikeID()==0?'Like':'Liked'}
							</button>
							<span id="num-like-comment-${Post.getPostID() }"
								class="pull-right text-muted">${Post.getNumLikes() }
								likes - ${Post.getNumComments() } comments</span>
						</div>
					</div>
					<div id="comment-list-${Post.getPostID() }"
						class="box-footer box-comments" style="display: block;">
						<c:forEach items="${Post.getListComments() }" var="com">
							<div class="box-comment">
								<div>
									<img class="img-circle img-sm"
										src="${com.getUser().getAvatarLink() }"> <a
										class="profile-click"
										href="profile?profileID=${com.getUser().getUserID() }"></a>
								</div>
								<div class="comment-text">
									<div style="display: block;">
										<a class="profile-click"
											href="profile?profileID=${com.getUser().getUserID() }">
											${com.getUser().getUserName() } <span
											class="comment_date text-muted pull-right">${com.getCreatedAt()}</span>
										</a>
									</div>
									${com.getContent() }
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="box-footer" style="display: block;">
						<img class="img-circle img-sm"
							src="${sessionScope.user_logged.getAvatarLink() }">
						<div class="img-push">
							<input id="comment-box-${Post.getPostID() }" type="text"
								class="form-control input-sm"
								placeholder="Press enter to post comment" />
						</div>
					</div>
					<script>

					// Bắt sự kiện khi nhấn Enter trong ô nhập tin nhắn
					$('#comment-box-${Post.getPostID() }').keyup(function(event) {
						if (event.keyCode === 13) {
							sendComment${Post.getPostID()}();
						}
					});
					function sendComment${Post.getPostID()}() {
						var comment_msg = $('#comment-box-${Post.getPostID()}').val();
						var user_id = ${sessionScope.user_logged.getUserID()};
						var post_id = ${Post.getPostID()};
						console.log(comment_msg)
						console.log("test")
						const currentDate = Date.now();
						const formattedDate = new Intl.DateTimeFormat('en-US', {
							  year: 'numeric',
							  month: 'long',
							  day: 'numeric',
							  hour: 'numeric',
							  minute: 'numeric',
							  second: 'numeric',
							  timeZone: 'UTC' // Adjust the time zone as needed
							}).format(currentDate);
						$.ajax({
							type : 'POST',
							url : '/FinalProject/api/send-comment',
							contentType: 'application/json',
							data : JSON.stringify({
								"userID" : ${user.getUserID() }+0,
								"postID" : ${Post.getPostID() }+0,
								"content" : comment_msg,
								"createdAt" : formattedDate
							}),
							success : function(response) {
								if(response != "Failed"){
									$('#comment-list-${Post.getPostID() }').append(`
											<div class="box-comment">
											<img class="img-circle img-sm"
												src="`+`${sessionScope.user_logged.getAvatarLink() }`+`">
											<div class="comment-text">
												<span class="username"> `+`${sessionScope.user_logged.getUserName() }`+`
													<span class="text-muted pull-right">`+formattedDate+`</span>
												</span> `+comment_msg+`
											</div>
										</div>`);
									text = $('#num-like-comment-${Post.getPostID() }').text();
									likesIndex = text.indexOf("likes - ")+8;
									commIndex = text.indexOf(" comment");
									commCount = (parseInt(text.substring(likesIndex, commIndex)) + 1).toString();
									$('#num-like-comment-${Post.getPostID() }').text(text.substring(0,likesIndex)+commCount+text.substring(commIndex));
									
								}
							},
							error : function(error) {
								console.log('Error:', error);
							}
						});

						// Xóa nội dung của ô nhập liệu sau khi gửi tin nhắn
						$('#comment-box-${Post.getPostID() }').val('');
					}
					let likeButton${Post.getPostID()} 
							= document.getElementById('like-button-${Post.getPostID()}')
					
					let likeID${Post.getPostID()}
							= ${listLike.get(st.index).getLikeID()}
					
					if(likeID${Post.getPostID()} == 0){

						likeButton${Post.getPostID()}.addEventListener('click', sendLike${Post.getPostID()})
						
					}else{

						likeButton${Post.getPostID()}.addEventListener('click', sendUnlike${Post.getPostID()})
						
					}
					function sendLike${Post.getPostID()}(){
						const currentDate = Date.now();
						const formattedDate = new Intl.DateTimeFormat('en-US', {
							  year: 'numeric',
							  month: 'long',
							  day: 'numeric',
							  hour: 'numeric',
							  minute: 'numeric',
							  second: 'numeric',
							  timeZone: 'UTC' // Adjust the time zone as needed
							}).format(currentDate);
						$.ajax({
							type : 'POST',
							url : '/FinalProject/api/send-like',
							contentType: 'application/json',
							data : JSON.stringify({
								"userID" : parseInt("${user.getUserID() }"),
								"postID" : parseInt("${Post.getPostID() }"),
								"createdAt" : formattedDate
							}),
							success : function(response) {
								if(response != "Failed"){
									likeID${Post.getPostID()} = parseInt(response)
									likeButton${Post.getPostID()}.removeEventListener('click',sendLike${Post.getPostID()})
									likeButton${Post.getPostID()}.addEventListener('click',sendUnlike${Post.getPostID()})
									likeButton${Post.getPostID()}.innerText="Liked"
									
									text = $('#num-like-comment-${Post.getPostID() }').text();
									
									re_text = text.replace(/\s/g, "");
									likesIndex = re_text.indexOf("likes");
									likesCount = (parseInt(re_text.substring(0, likesIndex).trim()) + 1).toString();
									$('#num-like-comment-${Post.getPostID() }').text(''+likesCount+text.substring(likesIndex));
								}
								console.log(response + " - add")
							},
							error : function(error) {
								console.log('Error:', error);
							}
						});
					}
					function sendUnlike${Post.getPostID()}(){
						$.ajax({
							type : 'DELETE',
							url : '/FinalProject/api/send-like',
							contentType: 'application/json',
							data : JSON.stringify({
								 "likeID": likeID${Post.getPostID()}
							}),
							success : function(response) {
								if(response != "Failed"){
									likeID${Post.getPostID()} = 0
									likeButton${Post.getPostID()}.removeEventListener('click',sendUnlike${Post.getPostID()})
									likeButton${Post.getPostID()}.addEventListener('click',sendLike${Post.getPostID()})
									likeButton${Post.getPostID()}.innerText="Like"
									
									text = $('#num-like-comment-${Post.getPostID() }').text();
									
									re_text = text.replace(/\s/g, "");
									likesIndex = re_text.indexOf("likes");
									likesCount = (parseInt(re_text.substring(0, likesIndex).trim()) - 1).toString();
									$('#num-like-comment-${Post.getPostID() }').text(''+likesCount+text.substring(likesIndex));
								}
								console.log(response+" - delete")
							},
							error : function(error) {
								console.log('Error:', error);
							}
						});
					}
					</script>
				</div>
			</div>
		</div>
	</c:forEach>
</ul>
<script src='<c:url value = "/resources/users/js/datetime_format.js"/>'></script>