<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<h2>ログイン</h2>

<form action="    login     " method="post">
<input type="text" name="id" placeholder="ID"><br>
<label>
<input type="text" name="password" placeholder="パスワード"><br>
	<input type="checkbox" name="chk_d_ps">
	パスワードを表示
</label><br>

<input type="submit" value="ログイン">

</form>




<%@include file="../footer.html" %>