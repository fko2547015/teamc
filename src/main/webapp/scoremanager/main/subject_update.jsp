<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main>
		<h2>科目情報変更</h2>
		
		<a href="<%= request.getContextPath() %>/SubjectList.action">戻る</a>
	</main>
</div>


<h2>科目情報変更</h2>

<form action="<%= request.getContextPath() %>/SubjectUpdateExecute.action" method="post">
	<label for="cd">科目コード</label>
	<input type="text" id="cd" name="cd" value="${subject.cd }" readonly>
	<br>
	
	<label for="subject_name">科目名</label>
		<input type="text" id="name" name="name" value="${subject.name }" readonly>
	<br>
	
	<button name="login">変更</button>

</form>
<a href="<%= request.getContextPath() %>/SubjectList.action">戻る</a>

<jsp:include page="/footer.html" />