<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<link href='<c:url value = "/resources/users/home.css"/>'
	rel="stylesheet" type="text/css">
<ul>
	<c:forEach items="${PostsList}" var="Post">
		<div class="container bootstrap snippets bootdey">
			<div class="col-md-8">
				<div class="box box-widget">
					<div class="box-header with-border">
						<div class="user-block">
							<img class="img-circle" src="${Post.getUser().getAvatarLink() }"
								alt="Avatar"> <span class="username"></span><a href="#">${Post.getUser().getUserName() }</a>
							<span class="description">${Post.getPostDate().toLocalDate().toString()}
								${Post.getPostDate().toLocalTime().toString()}</span>
						</div>
						<div class="box-tools">
							<button type="button" class="btn btn-box-tool"
								data-toggle="tooltip" title=""
								data-original-title="Mark as read">
								<i class="fa fa-circle-o"></i>
							</button>
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
							<button type="button" class="btn btn-box-tool"
								data-widget="remove">
								<i class="fa fa-times"></i>
							</button>
						</div>
					</div>

					<div class="box-body" style="display: block;">
						<p>${Post.getContent() }</p>
						<c:forEach items = "${Post.getListImageLink() }" var="link">
							<img class="img-responsive pad" src="${link}">
						</c:forEach>
						<div style="padding-bottom: 32px">
							<button style="float: left" type="button" class="btn btn-default btn-xs">
								<i class="fa fa-share"></i> Share
							</button>
							<button style="float: left" type="button" class="btn btn-default btn-xs">
								<i class="fa fa-thumbs-o-up"></i> Like
							</button>
							<span class="pull-right text-muted">${Post.getNumLikes() }
								likes - ${Post.getNumComments() } comments</span>
						</div>
					</div>
					<div class="box-footer box-comments" style="display: block;">
						<c:forEach items="${Post.getListComments() }" var="com">
							<div class="box-comment">
								<img class="img-circle img-sm"
									src="${com.getUser().getAvatarLink() }" alt="User Image">
								<div class="comment-text" >
									<span class="username"> ${com.getUser().getUserName() }
										<span class="text-muted pull-right">${com.getCreatedAt().toLocalDate().toString()} ${com.getCreatedAt().toLocalTime().toString()}</span>
									</span> ${com.getContent() }
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="box-footer" style="display: block;">
						<form action="#" method="post">
							<img class="img-circle img-sm"
								src="${Post.getUser().getAvatarLink() }"
								alt="Alt Text">
							<div class="img-push">
								<input type="text" class="form-control input-sm"
									placeholder="Press enter to post comment">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</ul>