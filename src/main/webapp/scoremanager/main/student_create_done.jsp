<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>学生情報登録</h2>

<p>登録が完了しました</p>

<a href="<%= request.getContextPath() %>/StudentCreate.action">戻る</a>
<a href="<%= request.getContextPath() %>/StudentList.action">学生一覧</a>

<jsp:include page="/footer.html" />