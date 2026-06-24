
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<jsp:include page="/header.jsp" />

<div class="container">
<jsp:include page="/side.jsp" />
	<main class="testlist-main">
	<div class="content">
		<h2>成績参照</h2>
		
		<!-- 科目検索フォーム -->
		<form class="search-form" method="get">
		<input type="hidden" name="mode" value="search">
		    <p class="form-title">科目情報</p>
		
		    入学年度:
		    <select name="f1">
		        <option value="">--------</option>
		        <c:forEach var="y" items="${ent_year_set}">
		            <option value="${y}" <c:if test="${y==f1}">selected</c:if>>
		                ${y}
		            </option>
		        </c:forEach>
		    </select>
		
		    クラス:
		    <select name="f2">
		        <option value="">--------</option>
		        <c:forEach var="c" items="${class_num_set}">
		            <option value="${c}" <c:if test="${c==f2}">selected</c:if>>
		                ${c}
		            </option>
		        </c:forEach>
		    </select>
		
		    科目:
		    <select name="f3">
		        <option value="">--------</option>
		        <c:forEach var="sub" items="${class_sub_set}">
		            <option value="${sub.cd}" <c:if test="${sub.cd==f3}">selected</c:if>>
		                ${sub.name}
		            </option>
		        </c:forEach>
		    </select>
		
		    <button>検索</button>
		    <br>
		    <c:if test="${not empty error_name}">
                <p class="error">${error_name}</p>
            </c:if>
		</form>
		<!-- 学生検索フォーム -->
		<form class="search-form"  method="get">
		<input type="hidden" name="mode" value="search">
		    <p class="form-title">学生情報</p>
		    学生番号:
		    <input type="text" name="f4" value="${f4}" required>
		    <button>検索</button>
		</form>
		
		<c:if test="${empty studentTests && empty subjectTests}">
		    <p class="guide">
		        科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
		    </p>
		</c:if>

		<hr>
		
		<c:if test="${not empty student_info}">
		    <p>${student_info}</p>
		</c:if>
		
		<c:if test="${not empty error_no}">
            <p class="error">${error_no}</p>
        </c:if>
		<!-- 科目検索結果 -->
		<c:if test="${not empty subjectTests}">
		    <h3 class="section-title">${f2} クラスの成績一覧</h3>
		
		    <table border="1">
		        <tr>
		            <th>学生番号</th>
		            <th>名前</th>
		
		            <!-- 回数 -->
		            <c:forEach var="n" items="${numList}">
		                <th>${n}回</th>
		            </c:forEach>
		        </tr>
		
		        <!-- データ -->
		        <c:forEach var="ts" items="${subjectTests}">
		            <tr>
		                <td>${ts.studentNo}</td>
		                <td>${ts.studentName}</td>
		
		                <c:forEach var="n" items="${numList}">
		                    <td>
		                        <c:choose>
		                            <c:when test="${ts.points[n] != null}">
		                                ${ts.points[n]}
		                            </c:when>
		                            <c:otherwise>-</c:otherwise>
		                        </c:choose>
		                    </td>
		                </c:forEach>
		
		            </tr>
		        </c:forEach>
		    </table>
		</c:if>
		
		<!-- 学生検索結果 -->
		<c:if test="${not empty studentTests}">
		    <h3 class="section-title">${student.name} の成績一覧</h3>
		
		    <table border="1">
		        <tr>
		            <th>科目名</th>
		            <th>科目コード</th>
		            <th>回数</th>
		            <th>点数</th>
		        </tr>
		
		        <c:forEach var="st" items="${studentTests}">
		            <tr>
		                <td>${st.subjectName}</td>
		                <td>${st.subjectCd}</td>
		                <td>${st.num}</td>
		                <td>${st.point}</td>
		            </tr>
		        </c:forEach>
		    </table>
		</c:if>
		</div>
	</main>
</div>

<jsp:include page="/footer.html" />
