<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href='<c:url value = "/resources/users/profile.css"/>'
	rel="stylesheet" type="text/css">
<body>
	<div class="mt-20 d-f jc-c al-c">
		<img src="${profile.getAvatarLink() }" style="width: 90%;">
	</div>

	<ul class="mt-20">
		<li>Tên: <span>${profile.getUserName() }</span></li>
		<li>Phone: <span>${profile.getNumPhone()}</span></li>
		<li>Email: <span>${profile.getEmail() == null?'Chưa có':profile.getEmail()}</span></li>
	</ul>
	<div class="mt-20 d-f jc-c al-c l0-r0">
		<button id="show-list-friend">Bạn bè</button>
	</div>
	<c:if test="${relation.getFromUserID() != relation.getToUserID()}">
		<div class="mt-20 d-f jc-c al-c l0-r0">
			<button id="open-or-create-message-folder">Nhắn tin</button>
		</div>
		<div class="mt-20 d-f jc-c al-c l0-r0">
			<button id="modify-relation" type="submit">
				<c:choose>
					<c:when test="${relation.isFriend()}">
			            Đã kết bạn
			        </c:when>
					<c:when test="${relation.isPending()}">
			            Đã gửi lời mời kết bạn
			        </c:when>
					<c:when test="${op_relation.isPending()}">
			            Đang chờ phản hồi kết bạn
			        </c:when>
					<c:otherwise>
			            Kết bạn
			        </c:otherwise>
				</c:choose>
			</button>
		</div>
		<div id="showControl" class="show-control"></div>
		<div class="mt-20 d-f jc-c al-c l0-r0">
			<button id="block-button">Block</button>
		</div>
		<div id="block-confirm" class="show-control"></div>
	</c:if>
</body>
<script type="text/javascript">
	var op_relation_id = "${op_relation.getRelationshipID()}";
	var relation_id = parseInt("${relation.getRelationshipID()}");
	var current_user_id = parseInt("${relation.getFromUserID()}")
	var profile_user_id = parseInt("${relation.getToUserID()}")
	if(${relation.getFromUserID() != relation.getToUserID()})
	document.getElementById('open-or-create-message-folder').addEventListener(
			'click', openMessfo)
	function openMessfo() {
		url = "/FinalProject/chat?toUserID=" + encodeURIComponent(${profile.getUserID()});
		window.location.href = url;
	}
	document.getElementById('show-list-friend').addEventListener(
			'click', showListFriend)
	function showListFriend() {
		url = "/FinalProject/friends?userID=" + encodeURIComponent(${profile.getUserID()});
		window.location.href = url;
	}
</script>
<script src='<c:url value = "/resources/users/js/relation_profile.js"/>'></script>