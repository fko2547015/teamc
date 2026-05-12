<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<aside>
	<div>
		<nav>
			<ul>
				<li><a href="${pageContext.request.contextPath}/scoremanager/main/menu.jsp">メニュー</a></li>
				<li><a href="<%= request.getContextPath() %>/StudentList.action">学生管理</a></li>
				<li><label>成績管理</label></li>
					<li><a href="${pageContext.request.contextPath}/scoremanager/main/test_regist.jsp">成績登録</a></li>
					<li><a href="${pageContext.request.contextPath}/scoremanager/main/test_list.jsp">成績参照</a></li>
				<li><a href="${pageContext.request.contextPath}/scoremanager/main/subject_list.jsp">科目管理</a></li>

			</ul>
		</nav>
	</div>
</aside>