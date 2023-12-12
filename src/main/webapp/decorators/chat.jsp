<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!------ Include the above in your HEAD tag ---------->


<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ALO HCMUTE</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	type="text/css" rel="stylesheet">
<link href='<c:url value = "/resources/users/chat.css"/>'
	rel="stylesheet" type="text/css">
</head>

<body
	style="display: flex; align-items: center; justify-content: center;">
	<table style="margin-top: 30x;">
		<tr>
			<td colspan="1"><%@ include file="/common/users/header.jsp"%>
			</td>
		</tr>
		<tr>
			<td class="header-block"></td>
		</tr>
		<tr>
			<td><decorator:body></decorator:body></td>
		</tr>
	</table>
</body>
</html>

