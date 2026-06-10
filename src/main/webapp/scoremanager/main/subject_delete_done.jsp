<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />

	<main class="subject-delete-complete-main">
	<div class="subject-delete-complete-box">
		<h2>科目情報削除</h2>
		
		<p>削除が完了しました</p>
		
		<a class="list" href="<%= request.getContextPath() %>/SubjectList.action">科目一覧</a>
		</div>
	</main>
</div>
<jsp:include page="/footer.html" />