<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="../../header.jsp" />

<div class="container">
<jsp:include page="../../side.jsp" />

<main class="form-main">
<div class="form-container">

    <h2>学生情報登録</h2>

    <form action="<%= request.getContextPath() %>/StudentCreateExecute.action" method="post">

        <div class="form-group">
            <label for="year">入学年度</label>
            <select id="year" name="ent_year">
                <option value="">--------</option>
                <c:forEach var="year" items="${ent_year_set}">
                    <option value="${year}" <c:if test="${year==f1}">selected</c:if>>
                        ${year}
                    </option>
                </c:forEach>
            </select>

            <c:if test="${not empty error_year}">
                <p class="error">${error_year}</p>
            </c:if>
        </div>

        <!-- 学生番号 -->
        <div class="form-group">
            <label for="no">学生番号</label>
            <input type="text"id="no"name="no"value="${no}"
                   placeholder="学生番号を入力してください"maxlength="10"inputmode="numeric"required>

            <c:if test="${not empty error_no}">
                <p class="error">${error_no}</p>
            </c:if>
        </div>

        <div class="form-group">
            <label for="name">氏名</label>
            <input type="text"id="name"name="name"value="${name}"
            	placeholder="氏名を入力してください"maxlength="30"required>
        </div>

        <div class="form-group">
            <label for="class">クラス</label>
            <select id="class" name="class_num">
                <option value="0">--------</option>
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}" <c:if test="${num==f2}">selected</c:if>>
                        ${num}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-actions">
            <button class="submit-btn" name="end">登録して終了</button>
        </div>

    </form>

    <a class="back-link" href="<%= request.getContextPath() %>/StudentList.action">← 一覧に戻る</a>

</div>
</main>
</div>
<jsp:include page="../../footer.html" />
