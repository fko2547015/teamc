<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />

<main class="test-main">
<div class="content">

<h2>成績管理</h2>

<!-- ✅ 検索フォーム -->
<form method="post" action="TestRegist.action">
    <input type="hidden" name="mode" value="search">

    入学年度：
    <select name="f1">
        <option value="0">--------</option>
        <c:forEach var="year" items="${ent_year_set}">
            <option value="${year}" <c:if test="${year==f1}">selected</c:if>>
                ${year}
            </option>
        </c:forEach>
    </select>

    クラス：
    <select name="f2">
        <option value="0">--------</option>
        <c:forEach var="num" items="${class_num_set}">
            <option value="${num}" <c:if test="${num==f2}">selected</c:if>>
                ${num}
            </option>
        </c:forEach>
    </select>

    科目：
    <select name="f3">
        <option value="0">--------</option>
        <c:forEach var="sub" items="${class_sub_set}">
            <option value="${sub.name}" <c:if test="${sub.name==f3}">selected</c:if>>
                ${sub.name}
            </option>
        </c:forEach>
    </select>

    回数：
    <select name="f4">
        <option value="0">--------</option>
        <c:forEach var="con" items="${class_con_set}">
            <option value="${con}" <c:if test="${con==f4}">selected</c:if>>
                ${con}
            </option>
        </c:forEach>
    </select>

    <button type="submit">検索</button>
</form>

<!-- ✅ 検索エラー -->
<c:if test="${not empty errors.f1}">
    <p style="color:red;">${errors.f1}</p>
</c:if>

<hr>

<!-- ✅ 検索結果 -->
<c:if test="${not empty tests}">

    <div>科目: ${subject.name}（${no}回）</div>

    <!-- ✅ 登録フォーム -->
    <form action="${pageContext.request.contextPath}/TestRegistExecute.action" method="post">

        <table border="1">
            <tr>
                <th>入学年度</th>
                <th>クラス</th>
                <th>学生番号</th>
                <th>氏名</th>
                <th>点数</th>
            </tr>

            <c:forEach var="test" items="${tests}">
                <tr>
                    <td>${test.student.entYear}</td>
                    <td>${test.classNum}</td>
                    <td>${test.student.no}</td>
                    <td>${test.student.name}</td>
                    <td>
                        <!-- ✅ 入力欄（値保持） -->
                        <input type="number"
                               name="point_${test.student.no}"
                               value="${test.point}">

                        <!-- ✅ 個別エラー -->
                        <c:if test="${not empty errors[test.student.no]}">
                            <div style="color:red; font-size: 0.9em;">
                                ${errors[test.student.no]}
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <div style="margin-top:10px;">
            <button type="submit">登録して終了</button>
        </div>

    </form>

</c:if>

</div>
</main>
</div>

<jsp:include page="/footer.html" />