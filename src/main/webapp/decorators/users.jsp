<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="vi">
<head>
	<!-- Site meta -->
	<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>ALO HCMUTE</title>
	<!-- CSS -->
	<link 
		href="https://images.squarespace-cdn.com/content/v1/5930dc9237c5817c00b10842/1557980730821-E0BL40VN22LDSYKQH91O/images.png"  
		rel="icon" type="image/x-icon">
	<link
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		rel="stylesheet" type="text/css">
	<link 
		href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
		rel="stylesheet" type="text/css">
	<link
		href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600"
		rel="stylesheet" type="text/css">
	<link 
		href='<c:url value = "/resources/users/style.css"/>'
	rel="stylesheet" type="text/css">
</head>

<body style="font-family: 'Open Sans', sans-serif;">

	<table border="1" style="width: 100%;">

		<tr>
			<td colspan="2"><%@ include file="/common/users/header.jsp"%>
			</td>
		</tr>
		<tr style ="margin-bottom:100px">
			<td><decorator:body></decorator:body></td>
		</tr>
		<tr>
			<td colspan="2"><%@ include file="/common/users/footer.jsp"%></td>
		</tr>
	</table>


	<script src="//code.jquery.com/jquery-3.2.1.slim.min.js"
		type="text/javascript"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		type="text/javascript"></script>
</body>
</html>