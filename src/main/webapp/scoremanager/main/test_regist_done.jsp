<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>成績管理</h2>

<p>登録が完了しました</p>

<a href="<%= request.getContextPath() %>/TestRegist.action">戻る</a>
<a href="<%= request.getContextPath() %>/TestList.action">成績参照</a>

<jsp:include page="/footer.html" />