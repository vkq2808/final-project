<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600"
	rel="stylesheet" type="text/css">
<link href='<c:url value = "/resources/users/login/sign-in.css"/>'
	rel="stylesheet" type="text/css">
<div class="row">
	<div class="col-md-6 mx-auto p-0">
		<div class="card">
			<div class="login-box">
				<div class="login-snip">
					<input id="tab-1" type="radio" name="tab" class="sign-in" ${isSignin }>
					<label for="tab-1" class="tab">Login</label> 
					<input id="tab-2" type="radio" name="tab" class="sign-up" ${isSignup }>
					<label for="tab-2" class="tab">Sign Up</label>
					<div class="login-space">
						<form method="post">
							<div class="login">
								<div class="group">
									<label for="user" class="label">Phone Number</label> <input
										name="phone" type="text" class="input"
										placeholder="Enter your phone">
								</div>
								<div class="group">
									<label for="pass" class="label">Password</label> <input
										name="password" type="password" class="input"
										data-type="password" placeholder="Enter your password">
								</div>
								<div class="group">
									<input id="check" type="checkbox" class="check" checked>
									<label for="check"><span class="icon"></span> Keep me
										Signed in</label>
								</div>
								<div class="group">
									<input type="submit" class="button" value="Sign In">
								</div>
								<c:if test="${not empty message}">
									<p style="color: red">${message}</p>
								</c:if>
								<div class="hr"></div>
								<div class="foot">
									<a href="#">Forgot Password?</a>
								</div>
							</div>
						</form>
						<form method="post">
							<div class="sign-up-form">
								<div class="group">
									<label for="user" class="label">Phone Number</label> <input
										name="phone" type="text" class="input"
										placeholder="Enter your phone number">
								</div>
								<div class="group">
									<label for="user" class="label">User Name</label> <input
										name="username" type="text" class="input"
										placeholder="Enter your Username">
								</div>
								<div class="group">
									<label for="pass" class="label">Password</label> <input
										name="password" type="password" class="input"
										data-type="password" placeholder="Enter your password">
								</div>
								<div class="group">
									<label for="pass" class="label">Repeat Password</label> <input
										name="repeat-password" type="password" class="input"
										data-type="password" placeholder="Repeat your password">
								</div>
								<div class="group">
									<input type="submit" class="button" value="Sign Up">
								</div>
								<c:if test="${not empty message}">
									<p style="color: red">${message}</p>
								</c:if>
								<div class="hr"></div>
								<div class="foot">
									<label for="tab-1">Already Member?</label>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
