<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../../header.jsp" />
<jsp:include page="../../side.jsp" />

<h2>学生情報登録</h2>

<form action="<%-- どこに送ろうかまだ未定 --%>" method="post">

	<label>入学年度<br>
	  <input type="text"
	         name="ent_year"
	         placeholder="--------"
	         maxlength="4"
	         pattern="\d{4}"
	         inputmode="numeric"
	         required>
	</label>
	<br>
	
	
	<label>学生番号<br>
		<input type="text" 
			name="no" 
			placeholder="学生番号を入力してください"
			maxlength="6"
			pattern="\d{6}"
	        inputmode="numeric"
	        required>
	</label>
	<br>
	
	<label>氏名<br>
		<input type="text" name="name" placeholder="氏名を入力してください">
	</label>
	<br>
	
	<label>クラス<br>
		<select name="class_num">
	        <option value="">選択してください</option>
	        <option value="101">101</option>
	        <option value="102">102</option>
	        <option value="110">110</option>
	        <option value="111">111</option>
	        <option value="201">201</option>
	        <option value="202">202</option>
	        <option value="211">211</option>
	        <option value="212">212</option>
	    </select>
	</label>
	<br>

	<input type="submit" name="end" value="登録して終了">


</form>

<a href="menu.jsp">戻る</a>



<jsp:include page="../../footer.html" />