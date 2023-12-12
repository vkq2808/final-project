<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<link href='<c:url value = "/resources/users/friend.css"/>'
	rel="stylesheet" type="text/css">

<div class="container">
	<div class="row">
		<c:forEach items="${FriendList }" var="friend" varStatus="ls">
			<div class="col-md-6 col-xl-4">
				<div class="card">
					<div class="card-body">
						<div class="media align-items-center">
							<span style="background-image: url(${friend.getAvatarLink()})"
								class="avatar avatar-xl mr-3"></span>
							<div class="media-body overflow-hidden">
								<h5 class="card-text mb-0">${friend.getUserName() }</h5>
								<p class="card-text text-uppercase text-muted">Bạn bè</p>
								<p class="card-text">
									${friend.getEmail() != null?friend.getEmail():'Chưa có Email'}<br>
									<span title="Phone">Phone: </span>${friend.getNumPhone()}
								</p>
							</div>
						</div>
						<a href="profile?profileID=${friend.getUserID() }" class="tile-link"></a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>