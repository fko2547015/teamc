<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />

<main class="form-main">
<div class="form-container">

    <h2>学生情報変更</h2>

    <form action="<%= request.getContextPath() %>/StudentUpdateExecute.action" method="post">

        <div class="form-group">
            <label for="year">入学年度</label>
            <input type="text" id="year" name="ent_year" value="${ent_year}" readonly class="readonly">
        </div>

        <div class="form-group">
            <label for="no">学生番号</label>
            <input type="text" id="no" name="no" value="${no}" readonly class="readonly">
        </div>

        <div class="form-group">
            <label for="name">氏名</label>
            <input type="text"id="name"name="name"value="${name}"
                   maxlength="30"required>
        </div>

        <div class="form-group">
            <label for="class">クラス</label>
            <select id="class" name="class_num">
                <option value="0">--------</option>
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}" <c:if test="${num==class_num}">selected</c:if>>
                        ${num}
                    </option>
                </c:forEach>
            </select>
        </div>

        
		
		<div class="form-group checkbox-group">
		    <label>
		        <input type="checkbox" name="is_attend" value="1"
		            <c:if test="${is_attend}">checked</c:if>>
		        在学中
		    </label>
		</div>



        <div class="form-actions">
            <button class="submit-btn">変更する</button>
        </div>

    </form>

    <a class="back-link" href="<%= request.getContextPath() %>/StudentList.action">
        ← 一覧に戻る
    </a>

</div>
</main>

</div>

<jsp:include page="/footer.html" />