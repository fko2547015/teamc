<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>成績管理</h2>

<p>登録が完了しました</p>

<a href="${pageContext.request.contextPath}/scoremanager/main/test_regist.jsp">戻る</a>
<a href="${pageContext.request.contextPath}/scoremanager/main/test_list.jsp">成績参照</a>

<jsp:include page="/footer.html" />