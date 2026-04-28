<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>学生管理</h2>
<%-- <a href="#">新規登録</a> --%>
<a href="${pageContext.request.contextPath}/scoremanager/main/student_create.jsp">新規登録</a>
<form>
	<label for="year">入学年度</label>
		<select id="year" name="f1">
			<option value="0">--------</option>
			<c:forEach var="year"  items="${ent_year_set }"><%-- ent_year_setというリストをjavaでつくる --%>
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
	<label><input type="checkbox" name="f3" value="1">在学中</label>
	<button>絞込み</button><%-- 学生情報を取得して一覧に表示 --%>
</form>

<c:choose>
	<%--<c:when test="${students.size() > 0 }"> DBからデータを取得して（DAO）studentsというリストをｊａｖａで作る --%>
	<c:when test="${not empty students}">
		<div>検索結果: ${students.size() }件</div>
		<table>
			<tr>
				<th>入学年度</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>クラス</th>
				<th>在学中</th>
				<th></th>
			</tr>
			<c:forEach var="student" items="${students }">
				<tr>
					<td>${student.entYear }</td>
					<td>${student.no }</td>
					<td>${student.name }</td>
					<td>${student.classNum }</td>
					<td>
						<c:choose>
							<c:when test="${student.isAttend() }">
								〇
							</c:when>
							<c:otherwise>
								×
							</c:otherwise>
						</c:choose>
					</td>
					<td><a href="${pageContext.request.contextPath}/scoremanager/main/student_update.js">変更</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<div>学生情報が存在しませんでした。</div>
	</c:otherwise>
</c:choose>


<jsp:include page="/footer.html" />