<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>成績管理</h2>
<form mode="post">
	<input type="hidden" name="mode" value="search">
	<label for="year">入学年度</label>
		<select id="year" name="f1">
			<option value="0">--------</option>
			<c:forEach var="year"  items="${ent_year_set }">
				<option value="${year }" <c:if test="${year==f1 }">selected</c:if>>${year }</option>
			</c:forEach>
		</select>
	<label for="class">クラス</label>
		<select id="class" name="f2">
			<option value="0">--------</option>
			<c:forEach var="num"  items="${class_num_set }">
				<option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num }</option>
			</c:forEach>
		</select>
	<label for="sub">科目</label>
		<select id="sub" name="f3">
			<option value="0">--------</option>
			<c:forEach var="sub"  items="${class_sub_set }">
				<option value="${sub.name }" <c:if test="${sub.name==f3 }">selected</c:if>>${sub.name }</option>
			</c:forEach>
		</select>	
	<label for="con">回数</label>
		<select id="con" name="f4">
			<option value="0">--------</option>
			<c:forEach var="con"  items="${class_con_set }">
				<option value="${con }" <c:if test="${con==f4 }">selected</c:if>>${con }</option>
			</c:forEach>
		</select>
	<button>検索</button>
</form>

<c:if test="${not empty errors }">
	<p>${errors.f1 }</p>
</c:if>

<c:choose>
	<c:when test="${not empty tests}">
<%-- DBからデータを取得して（DAO）testsというリストをｊａｖａで作る --%>
	<div>科目: ${subject.name } (${no }回)</div>
	<form action="<%= request.getContextPath() %>/TestRegistExecute.action" method="post">
	<table>
		<tr>
			<th>入学年度
			</th>
			<th>クラス</th>
			<th>学生番号</th>
			<th>氏名</th>
			<th>点数</th>
		</tr>
		<c:forEach var="test" items="${tests }">
			<tr>
				<td>${test.student.entYear }</td>
				<td>${test.classNum }</td>
				<td>${test.student.no }</td>
				<td>${test.student.name }</td>
				<td><input type="text" name="point_${test.student.no }" value="${test.point }"></td>
			</tr>
		</c:forEach>
	</table>
	<button name="fin">登録して終了</button>
	</form>
	</c:when>
</c:choose>

<input type="hidden" name="regist" value="${test.student.no }">
<input type="hidden" name="count" value="${f4 }">
<input type="hidden" name="subject" value="${f3 }">
<jsp:include page="/footer.html" />