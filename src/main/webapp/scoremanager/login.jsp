<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../header.jsp" />
<jsp:include page="../side.jsp" />

<h2>ログイン</h2>

<form action="login" method="post">
<input type="text" name="id" placeholder="ID"><br>
<label>
<input type="text" name="password" placeholder="パスワード"><br>
	<input type="checkbox" name="chk_d_ps">
	パスワードを表示
</label><br>

<input type="submit" value="ログイン">

</form>


<jsp:include page="../footer.html" />