<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>MyTest</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<h3>변수 처리</h3>
<%-- <c:set var="mynum" value="4444" /> --%>
<h3>${mynum}</h3>	<!-- // 이 코드를 많이 사용 -->
<h3><c:out value="${mynum}" /></h3>	<!-- // 번거롭군 코드당 -->
<h3>제어 구조</h3>
<c:choose>
	<c:when test="${mynum % 2 == 1}">
		<h4>${mynum}은 홀수</h4>
	</c:when>
	<c:when test="${mynum % 2 == 0}">
		<h4>${mynum}은 짝수</h4>
	</c:when>
	
</c:choose>
<h4>구구단</h4>
<c:forEach var="i" begin="1" end="9">
	<c:forEach var="j" begin="1" end="9">
		${j} * ${i} = ${i*j}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</c:forEach>
	<br>
</c:forEach>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
