<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main class="subject-main">
	<div class="content">
		<h2>科目管理</h2>
		<a class="create-btn" href="<%= request.getContextPath() %>/SubjectCreate.action">新規登録</a>
		
		<table>
			<tr>
				<th>科目コード</th>
				<th>科目名</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="subject" items="${subjects}">
				<tr>
					<td>${subject.cd }</td>
					<td>${subject.name }</td>
					<td>
					    <a href="<%= request.getContextPath() %>/SubjectUpdate.action?cd=${subject.cd}">変更</a>
					</td>
					<td>
					    <a class="delete" href="<%= request.getContextPath() %>/SubjectDelete.action?cd=${subject.cd}">削除</a>
					</td>
					
				</tr>
			</c:forEach>
		</table>
	</div>	
	</main>
</div>

<jsp:include page="/footer.html" />