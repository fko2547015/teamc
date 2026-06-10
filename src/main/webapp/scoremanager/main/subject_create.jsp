<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main class="form-main">
	<div class="form-container">
		<h2>科目情報登録</h2>
		
		<form action="<%= request.getContextPath() %>/SubjectCreateExecute.action" method="post">
		
			<div class="form-group">
		    <label>科目コード<br>
		        <input type="text"
		               name="cd"
		               placeholder="科目コードを入力してください"
		               maxlength="3"
		               required>
		    </label>
		    <br>
		 	</div>
		 	<div class="form-group">
		    <label>科目名<br></label>
		        <input type="text"
		               name="name"
		               placeholder="科目名を入力してください"
		               maxlength="20"
		               required>
		    </div>
		    
		    
		    <div class="form-actions">
		    <input class="submit-btn" type="submit" value="登録">
			</div>
		</form>
		<a href="<%= request.getContextPath() %>/SubjectList.action">←一覧に戻る</a>
		</div>
	</main>
</div>

<jsp:include page="/footer.html" />