<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<h2>Recent Posts</h2>
<ul id="post-list">
    <c:forEach items="${PostsList}" var="PostUser">
        <li class="post-item">
            <h2 class="post-title">${PostUser.getUser().getUserName() }%></h2>
            <p class="post-content">${PostUser.getPost().getContent() }</p>
        </li>
    </c:forEach>
</ul>