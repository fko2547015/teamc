<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main class="form-main">
	<div class="form-container">
		<h2>科目情報変更</h2>
			<form action="<%= request.getContextPath() %>/SubjectUpdateExecute.action" method="post">
			
			<div class="form-group">
			<label for="cd">科目コード</label>
			<input type="text" id="cd" name="cd" value="${subject.cd }" readonly>
			<br>
			<c:if test="${not empty error_cd}">
	            <p class="error">${error_cd}</p>
	        </c:if>
			</div>
			<div class="form-group">
			<label for="subject_name">科目名</label>
				<input type="text" id="name" name="name" value="${subject.name }" required>
			<br>
			</div>
			<div class="form-actions">
			<button class="submit-btn" name="login">変更</button>
			</div>
		</form>
		<a href="<%= request.getContextPath() %>/SubjectList.action">戻る</a>
		</div>
	</main>
</div>
<jsp:include page="/footer.html" />