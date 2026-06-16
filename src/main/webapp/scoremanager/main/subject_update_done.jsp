<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main class="update-complete-main">
	<div class="update-complete-box">
		<h2>科目情報変更</h2>
		
		<p>変更が完了しました</p>
		<div class="update-actions">
		<a href="<%= request.getContextPath() %>/SubjectList.action">科目一覧</a>
		</div>
	</div>
	</main>
</div>

<jsp:include page="/footer.html" />