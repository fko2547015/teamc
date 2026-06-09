<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main>
		<h2>科目情報削除</h2>
		
		<p>「${subject.name}(${subject.cd})」を削除してもよろしいですか</p>
		
		<form action="SubjectDeleteExecute.action" method="post">
		    <input type="hidden" name="cd" value="${subject.cd}">
		    <input type="hidden" name="name" value="${subject.name}">
		    
		    <input type="submit" value="削除">
		</form>
		
		
		<a href="<%= request.getContextPath() %>/SubjectList.action">戻る</a>
	</main>
</div>


<jsp:include page="/footer.html" />