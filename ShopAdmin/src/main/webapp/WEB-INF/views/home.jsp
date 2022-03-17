<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<p><a href="/admin/logout">로그아웃</a></p>
<p><a href="/admin/login">로그인</a></p>
<h1>
	Shop Admin Hello world!  
</h1>


<P>  The time on the server is ${serverTime}. </P>
<p> 사용자아이뒤 : ${a_id} </p>
<p> 사용자이이름 : ${a_name} </p>
</body>
</html>
