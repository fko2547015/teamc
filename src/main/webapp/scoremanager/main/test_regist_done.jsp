<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main class="test-complete-main">
	<div class="test-complete-box">
		<h2>成績管理</h2>
		
		<p>登録が完了しました</p>
		<div class="test-actions">
		<a class="back" href="<%= request.getContextPath() %>/TestRegist.action">戻る</a>
		<a class="list" href="<%= request.getContextPath() %>/TestList.action">成績参照</a>
		</div>
	</div>
	</main>
</div>
<jsp:include page="/footer.html" />