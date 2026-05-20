<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>学生情報変更</h2>


<form action="<%= request.getContextPath() %>/StudentUpdateExecute.action" method="post">

	<label for="year">入学年度</label>
		<input type="text" id="year" name="ent_year" value="${ent_year }" readonly>
	<br>

	<label for="no">学生番号</label>
		<input type="text" id="no" name="no" value="${no }" readonly>
	<br>
	
	<label for="name">氏名</label>
		<input type="text" name="name" value="${name }" maxlength="30" required>
	<br>
	
	<label for="class">クラス</label>
		<select id="class" name="class_num">
			<option value="0">--------</option>
			<c:forEach var="num"  items="${class_num_set }">
				<option value="${num }" <c:if test="${num==class_num }">selected</c:if>>${num }</option>
			</c:forEach>
		</select>
	<br>
	<label>在学中<input type="checkbox" name="is_attend" value="${is_attend }"
       <c:if test="${is_attend == true}">checked</c:if>>
	</label>
	<br>
	<button name="login">変更</button>
</form>

<a href="<%= request.getContextPath() %>/StudentList.action">戻る</a>


<jsp:include page="/footer.html" />