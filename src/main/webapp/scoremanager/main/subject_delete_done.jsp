<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>科目情報削除</h2>

<p>削除が完了しました</p>

<a href="${pageContext.request.contextPath}/scoremanager/main/subject_list.jsp">科目一覧</a>

<jsp:include page="/footer.html" />