<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main>
		<h2>学生情報変更</h2>
		
		<p>変更が完了しました</p>
		
		
		<a href="<%= request.getContextPath() %>/StudentList.action">学生一覧</a>
	</main>
</div>

<jsp:include page="/footer.html" />