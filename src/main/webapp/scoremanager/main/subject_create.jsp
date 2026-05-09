<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>科目情報登録</h2>

<form action="<%-- どこに送ろうかまだ未定 --%>" method="post">

    <label>科目コード<br>
        <input type="text"
               name="subject_cd"
               placeholder="科目コードを入力してください"
               maxlength="3"
               required>
    </label>
    <br>
    
    <label>科目名<br>
        <input type="text"
               name="subject_name"
               placeholder="科目名を入力してください"
               maxlength="20"
               reqiired>
    </label>
    <br>
    <input type="submit" value="登録">

</form>
<jsp:include page="/footer.html" />