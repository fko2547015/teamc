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
	<label>学生参照<input type="text" name="f4" value="${f4 }"></label>
	<button>検索</button><%-- 自画面に遷移、科目の検索条件を削除して一覧表示 --%>
</form>
<label><p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p></label>

<input type="hidden" name="f" value="${sj }">
<input type="hidden" name="f" value="${st }">
<jsp:include page="/footer.html" />