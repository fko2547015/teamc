<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="header.jsp" />
<jsp:include page="side.jsp" />

<h2>学生管理</h2>
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
<label><input type="checkbox" name="f3" value="now">在学中</label>
<a herf="<%--学生登録画面へ--%>">新規登録</a>
<button>絞込み</button><%-- 学生情報を取得して一覧に表示 --%>
<div>検索結果: <%-- 件数も表示 --%></div>
<table>
	<tr>
		<th>入学年度</th>
		<th>学生番号</th>
		<th>氏名</th>
		<th>クラス</th>
		<th>在学中</th>
		<th></th>
	</tr>
	<c:forEach var="student" items="%{students }">
		<tr>
			<td></td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="footer.html" />