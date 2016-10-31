<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gallerie</title>
<s:url value="/resources/css/bonjour.css" var="coreStyle" />
<link href="${coreStyle}" rel="stylesheet" />
</head>
<body>
<h2>jolie gallerie</h2>
<c:forEach items="${images}" var="img" >
	<div class="imageThumb">
		<img src="images/data/${img.id}" width="150" height="150" /><br />
		<span>${img.fileName}</span>
	</div>
</c:forEach>
</body>
</html>