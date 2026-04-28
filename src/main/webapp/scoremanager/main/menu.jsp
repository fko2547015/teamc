<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../../header.jsp" />
<jsp:include page="../../side.jsp" />
<div>
	<h2>メニュー</h2>
	<nav>
		<div>
			<a href="<%= request.getContextPath() %>/StudentList.action">学生管理</a>
		</div>
	
		<div>
			<label>成績管理</label>
				<a href="<%-- 成績管理一覧画面へ --%>">成績登録</a>
				<a href="<%-- 成績参照検索画面へ --%>">成績参照</a>
		</div>
	
		<div>	
			<a href="<%--科目管理画面へ--%>">科目管理</a>
		</div>
	
	</nav>
</div>


<jsp:include page="../../footer.html" />