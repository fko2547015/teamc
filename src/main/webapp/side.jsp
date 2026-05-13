<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<aside>
	<div>
		<nav>
			<ul>
				<li><a href="${pageContext.request.contextPath}/scoremanager/main/menu.jsp">メニュー</a></li>
<<<<<<< HEAD
				<li><a href="StudentList.action">学生管理</a></li>
				<li>
				<label>成績管理</label>
					<ul>
						<li><a href="TestRegist.action">成績登録</a></li>
						<li><a href="TestList.action">成績参照</a></li>
					</ul>
				</li>
				<li><a href="SubjectList.action">科目管理</a></li>
=======
				<li><a href="<%= request.getContextPath() %>/StudentList.action">学生管理</a></li>
				<li><label>成績管理</label></li>
					<li><a href="${pageContext.request.contextPath}/scoremanager/main/test_regist.jsp">成績登録</a></li>
					<li><a href="${pageContext.request.contextPath}/scoremanager/main/test_list.jsp">成績参照</a></li>
				<li><a href="${pageContext.request.contextPath}/scoremanager/main/subject_list.jsp">科目管理</a></li>
>>>>>>> branch 'master' of https://github.com/fko2547015/teamc.git

			</ul>
		</nav>
	</div>
</aside>