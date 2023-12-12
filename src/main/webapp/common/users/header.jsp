<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
</head>
<style>
.nav-link {
	text-wrap: nowrap !important;
}

.form-control.mr-sm-2 {
	width: 80% !important;
}

.form-inline.my-2.my-lg-0 {
	flex-wrap: nowrap;
}

#mySearchForm {
	width: 80%;
}
</style>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		<a class="navbar-brand" href="trang-chu">Trang chủ</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="trang-chu">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="chat">Chat</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="collapse" data-target="#dropDownTarget"
					aria-haspopup="true" aria-expanded="false"> People </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown"
						id="dropDownTarget">
						<a class="dropdown-item" href="friends">Friends</a> <a
							class="dropdown-item" href="#">Pending inviting</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Blocked people</a>
					</div></li>
				<li id="mySearchForm">
					<form class="form-inline my-2 my-lg-0">
						<input class="form-control mr-sm-2" type="search"
							placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
					</form>
				</li>
			</ul>
		</div>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent-2"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent-2">
			<ul class="navbar-nav ml-auto">
				<c:if test="${not empty sessionScope.username}">
					<li class="nav-item"><a class="nav-link" href="profile">${sessionScope.username}</a></li>
					<li class="nav-item"><a class="nav-link" href="sign-out">Logout</a></li>
				</c:if>
				<c:if test="${empty sessionScope.username}">
					<li class="nav-item"><a class="nav-link" href="login">Login</a></li>
				</c:if>
			</ul>
		</div>
	</nav>
</body>