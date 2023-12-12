<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<!-- Site meta -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ALO HCMUTE</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	type="text/css" rel="stylesheet">
<link href='<c:url value = "/resources/home.css"/>' rel="stylesheet"
	type="text/css">
</head>

<body style="font-family: 'Open Sans', sans-serif;">

	<table border="1" style="width: 100%;">

		<tr>
			<td colspan="2"><%@ include file="/common/users/header.jsp"%>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="header-block"></td>
		</tr>
		<tr class="main-body">
			<td style="width:20%;overflow:hidden; position:fixed;left:0px;top:50px;bottom:0px;"> <%@ include file="/views/users/profile/left.jsp"%></td>
			<td style="width:80%;"><decorator:body></decorator:body></td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
	</table>
</body>
</html>