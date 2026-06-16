<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../../header.jsp" />
<!--<jsp:include page="../../side.jsp" />-->

	<main class="form-main">
	<div class="subject-complete-box">
		<h2>ログアウト</h2>
		
		<label>
		<p>ログアウトしました</p>
		</label>
		<div class="update-actions">
		<a href="<%= request.getContextPath() %>/Login.action">ログイン画面へ</a>
		</div>
	</div>
	</main>
	

<jsp:include page="../../footer.html" />