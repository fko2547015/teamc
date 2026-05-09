<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>学生情報登録</h2>

<p>登録が完了しました</p>

<a href="${pageContext.request.contextPath}/scoremanager/main/student_create.jsp">戻る</a>
<a href="${pageContext.request.contextPath}/scoremanager/main/student_list.jsp">学生一覧</a>

<jsp:include page="/footer.html" />