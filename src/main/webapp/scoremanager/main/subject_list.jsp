<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>科目管理</h2>
<a href="${pageContext.request.contextPath}/scoremanager/main/subject_create.jsp">新規登録</a>

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
			    <a href="${pageContext.request.contextPath}/scoremanager/main/subject_update.jsp?cd=${subject.cd}">変更</a>
			</td>
			<td>
			    <a href="${pageContext.request.contextPath}/scoremanager/main/subject_delete.jsp?cd=${subject.cd}">削除</a>
			</td>
			
		</tr>
	</c:forEach>
</table>

<jsp:include page="/footer.html" />