<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../header.jsp" />
<!--<jsp:include page="../side.jsp" />-->


	<main class="login">
	<h2>ログイン</h2>
	<c:if test="${not empty error}">
	    <p>${error}</p>
	</c:if>
	
	<form action="<%= request.getContextPath() %>/LoginExecute.action" method="post">
	
	<input type="text" name="id" placeholder="ID" required><br>
	<input type="password" id="password" name="password" placeholder="パスワード" required><br>
	<div class="checkbox-area">
		<label>  
		  <input type="checkbox" id="chk_d_ps">
		  パスワードを表示
		</label>
	</div>
	
	<script>
	  const pwd = document.getElementById("password");
	  const chk = document.getElementById("chk_d_ps");
	
	  chk.addEventListener("change", () => {
	    pwd.type = chk.checked ? "text" : "password";
	  });
	</script>
	
	<input type="submit" value="ログイン">
	
	</form>
	</main>


<jsp:include page="../footer.html" />
