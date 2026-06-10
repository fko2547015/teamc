<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main class="complete-main">
	<div class="complete-box">
		<h2>学生情報登録</h2>
		
		<p>登録が完了しました</p>
		<div class="complete-actions">
		<a class ="back" href="<%= request.getContextPath() %>/StudentCreate.action">もう一度登録</a>
		<a class ="list" href="<%= request.getContextPath() %>/StudentList.action">学生一覧</a>
	</main>
</div>
<jsp:include page="/footer.html" />