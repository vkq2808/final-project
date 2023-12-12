<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<div class="container">
	<div class="messaging">
		<div class="inbox_msg">
			<div class="inbox_people">
				<div class="headind_srch chat_list">
					<div class="recent_heading">
						<h4>Recent</h4>
					</div>
					<div class="srch_bar">
						<div class="stylish-input-group">
							<input type="text" class="search-bar" placeholder="Search">
							<span class="input-group-addon">
								<button type="button">
									<i class="fa fa-search" aria-hidden="true"></i>
								</button>
							</span>
						</div>
					</div>
				</div>
				<div class="inbox_chat">
					<c:forEach items="${listmessfo}" var="chat">
						<div id="messfo${chat.getMessageFolderID()}"
							class="chat_list ${chat.getMessageFolderID() == messfo ? 'active_chat' : ''}">
							<div class="chat_people">
								<div class="chat_img">
									<img src="${chat.getMessageFolderAvatarLink()}" alt="sunil">
								</div>
								<div class="chat_ib">
									<h5>${chat.getMessageFolderName()}
										<span class="chat_date">${chat.getLatestMessage().getCreatedAt()}</span>
									</h5>
									<p>${chat.getLatestMessage().getContent()}</p>
								</div>
							</div>
						</div>
						<script>
							// Lấy ra phần tử div cần xử lý sự kiện click
							var chatDiv = document
									.getElementById("messfo${chat.getMessageFolderID()}");

							// Kiểm tra xem phần tử có tồn tại không trước khi thêm sự kiện
							if (chatDiv) {
								// Thêm sự kiện click cho mỗi phần tử div
								chatDiv.addEventListener('click', function() {
									// Chuyển hướng đến trang chat với tham số messfo
									window.location.href = "./chat?messfo="
											+ "${chat.getMessageFolderID()}";
								});
							}
						</script>
					</c:forEach>
				</div>
			</div>


			<div class="mesgs">
				<div id="msg_chat" class="msg_history" style="overflow-y: scroll;">
					<c:if test="${hasMessFo eq 'true' }">
						<c:forEach items="${listmsg }" var="msg">
							<c:if test="${msg.getSenderID() ne user.getUserID() }">
								<div class="incoming_msg">
									<div class="incoming_msg_img">
										<img src="${msg.getSender().getAvatarLink() }" alt="sunil">
									</div>
									<div class="received_msg">
										<h6>${msg.getSender().getUserName() }</h6>
										<div class="received_withd_msg">
											<p>${msg.getContent() }</p>
											<span class="time_date">${msg.getCreatedAt() }</span>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${msg.getSenderID() eq user.getUserID() }">
								<div class="outgoing_msg">
									<div class="sent_msg">
										<p>${msg.getContent() }</p>
										<span class="time_date">${msg.getCreatedAt() }</span>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
				</div>
				<div class="type_msg">
					<div class="input_msg_write">
						<input id="messageInput" type="text" class="write_msg"
							placeholder="Type a message" />
						<button id="sendMessageBtn" class="msg_send_btn" type="button">
							<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src='<c:url value = "/resources/users/js/datetime_format.js"/>'></script>
<script>
	$("#msg_chat").animate({ scrollTop: 20000000 }, "slow");
	// Bắt sự kiện click chuột vào nút gửi tin nhắn
	$('#sendMessageBtn').click(function() {
		sendMessage();
	});

	// Bắt sự kiện khi nhấn Enter trong ô nhập tin nhắn
	$('#messageInput').keyup(function(event) {
		if (event.keyCode === 13) {
			sendMessage();
		}
	});

	function sendMessage() {
		// Lấy giá trị tin nhắn từ ô nhập liệu
		var message = $('#messageInput').val();
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
		// Gọi đến AJAX để gửi tin nhắn
		$.ajax({
			type : 'POST',
			url : '/FinalProject/api/send-message',
			contentType: 'application/json',
			data : JSON.stringify({
				"senderID" : ${user.getUserID() }+0,
				"messageFolderID" : ${messfo} +0,
				"content" : message,
				"createdAt" : formattedDate
			}),
			success : function(response) {
				if(response === "Success"){
					
					$('#msg_chat').append(`
									<div class="outgoing_msg">
										<div class="sent_msg">
											<p>`+message+`</p>
											<span class="time_date">`+formattedDate+`</span>
										</div>
									</div>`);
					$("#msg_chat").animate({ scrollTop: 20000000 }, "slow");
				}
			},
			error : function(error) {
				console.log('Error:', error);
			}
		});

		// Xóa nội dung của ô nhập liệu sau khi gửi tin nhắn
		$('#messageInput').val('');
	}
</script>
