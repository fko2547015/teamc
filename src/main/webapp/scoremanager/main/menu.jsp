<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../../header.jsp" />

<div class="container">
<jsp:include page="../../side.jsp" />
	<main class="menu">
		<div>
			<h2>メニュー</h2>
			<nav>
				<div>
					<a href="<%= request.getContextPath() %>/StudentList.action">学生管理</a>
				</div>
			
				<div>
					<label>成績管理</label>
						<a href="<%= request.getContextPath() %>/TestRegist.action">成績登録</a>
						<a href="<%= request.getContextPath() %>/TestList.action">成績参照</a>
				</div>
			
				<div>	
					<a href="<%= request.getContextPath() %>/SubjectList.action">科目管理</a>
				</div>
			
			</nav>
		</div>
	</main>
</div>

<jsp:include page="../../footer.html" />