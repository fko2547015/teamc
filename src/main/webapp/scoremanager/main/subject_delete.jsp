<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>科目情報削除</h2>

<p>「Javaプログラミング基礎(FO2)」を削除してもよろしいですか</p>

<form action="<%--科目削除サーブレット--%>" method="post">
    <input type="hidden" name="subuject_cd" value="${subject_cd}">
    <input type="hidden" name="subject_name" value="${subject_name}">
    
    <input type="submit" value="削除">
</form>


<a href="<%--科目管理一覧へ--%>">戻る</a>


<jsp:include page="/footer.html" />