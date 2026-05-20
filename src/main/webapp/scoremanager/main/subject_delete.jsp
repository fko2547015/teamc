<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>科目情報削除</h2>

<p>「${name}(${cd})」を削除してもよろしいですか</p>

<form action="SubjectDeleteExecute.action" method="post">
    <input type="hidden" name="cd" value="${cd}">
    <input type="hidden" name="name" value="${name}">
    
    <input type="submit" value="削除">
</form>


<a href="<%= request.getContextPath() %>/subject_list.jsp">戻る</a>


<jsp:include page="/footer.html" />