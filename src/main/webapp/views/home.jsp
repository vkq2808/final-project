<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<ul>
	<c:forEach items="${PostsList}" var="PostUser">
		<div>
			<h1>${PostUser.getUser().getUserName() }</h1>
			
			<h6>${PostUser.getPost().getContent() }</h6>
			
			<c:if test="${PostUser.isHasImage() eq true }">
	 			<img class="post-images-2" src="${PostUser.getPost().getImageLink() }">
			</c:if>	
		</div>
	</c:forEach>
</ul>