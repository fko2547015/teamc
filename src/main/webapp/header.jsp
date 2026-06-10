<%@ page import="bean.Teacher" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>得点管理システム</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	
</head>
<body>
<div id="header_area">
	<h1 id="header_h1">得点管理システム</h1>
	<% Teacher teacher = (Teacher) session.getAttribute("teacher"); 
	if (teacher != null) { %>
	<span><%= teacher.getName() %>様</span>
	<a href="<%= request.getContextPath() %>/Logout.action">ログアウト</a>
	<% } %>
	
</div>