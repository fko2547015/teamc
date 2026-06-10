<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main class="subject-complete-main">
	<div class="subject-complete-box">
		<h2>科目情報登録</h2>
		
		<p>登録が完了しました</p>
		<div class="subject-actions">
		<a class="back" href="<%= request.getContextPath() %>/SubjectCreate.action">戻る</a>
		<a class="list" href="<%= request.getContextPath() %>/SubjectList.action">科目一覧</a>
		</div>
	</div>
	</main>
</div>


<jsp:include page="/footer.html" />