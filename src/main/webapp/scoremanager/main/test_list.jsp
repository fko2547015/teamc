<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />
<jsp:include page="/side.jsp" />

<h2>成績参照</h2>
<form>
	<label><p>科目情報</p></label>
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
				<option value="${sub }" <c:if test="${sub==f3 }">selected</c:if>>${sub }</option>
			</c:forEach>
		</select>	
	<button>検索</button><%-- 自画面に遷移、学生のほうの検索条件を削除して一覧表示 --%>
</form>
<form>
	<label><p>学生情報</p></label>
	<label><input type="text" name="f4" value="${f4 }"></label>
	<button>検索</button><%-- 自画面に遷移、科目の検索条件を削除して一覧表示 --%>
</form>
<label><p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p></label>





<%-- DBからデータを取得して（DAO）testsというリストをｊａｖａで作る --%>
<div>科目: ${tests.sub() }(${tests.con() })</div>
<table>
	<tr>
		<th>入学年度</th>
		<th>クラス</th>
		<th>学生番号</th>
		<th>氏名</th>
		<th>点数</th>
	</tr>
	<c:forEach var="test" items="%{tests }">
		<tr>
			<td>${test.entYear }</td>
			<td>${test.classNum }</td>
			<td>${test.studentNum }</td>
			<td>${test.name }</td>
			<td><input type="text" name="point_${test.student.no }" value="${test.point }"></td>
		</tr>
	</c:forEach>
</table>
<input type="button" name="fin" value="登録して終了" onclick="location.href='<%--成績登録完了画面 --%>>'">

<input type="hidden" name="regist" value="${test.student.no }">
<input type="hidden" name="count" value="${f4 }">
<input type="hidden" name="subject" value="${f3 }">
<jsp:include page="/footer.html" />