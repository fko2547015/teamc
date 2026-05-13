<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../../header.jsp" />
<jsp:include page="../../side.jsp" />

<h2>学生情報登録</h2>

<form action="<%= request.getContextPath() %>/StudentCreateExecute.action" method="post">

	<label for="year">入学年度</label>
		<select id="year" name="ent_year">
			<option value="0">--------</option>
			<c:forEach var="year"  items="${ent_year_set }"><%-- ent_year_setというリストをjavaでつくる --%>
				<option value="${year }" <c:if test="${year==f1 }">selected</c:if>>${year }</option>
			</c:forEach>
		</select>
	<br>

	<label for="no">学生番号</label>
		<input type="text" id="no" name="no" value="${no }"
			placeholder="学生番号を入力してください" maxlength="10" inputmode="numeric" required>
	<br>
	
	<label>氏名</label>
		<input type="text" name="name" value="${name }" placeholder="氏名を入力してください">
	<br>
	
	<label for="class">クラス</label>
		<select id="class" name="class_num">
			<option value="0">--------</option>
			<c:forEach var="num"  items="${class_num_set }">
				<option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num }</option>
			</c:forEach>
		</select>
	<br>

	<button name="end">登録して終了</button>
</form>

<a href="<%= request.getContextPath() %>/Menu.action">戻る</a>



<jsp:include page="../../footer.html" />