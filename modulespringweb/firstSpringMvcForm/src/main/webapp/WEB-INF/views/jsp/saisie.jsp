<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exemple saisie</title>
<s:url value="/resources/css/bonjour.css" var="coreStyle" />
<link href="${coreStyle}" rel="stylesheet" />
</head>
<body>
<h2>${infos}</h2>
<sf:form commandName="message" action="save" method="post">
	<sf:input path="titre" type="text" /><br />
	<sf:textarea path="corps" /><br />
	<sf:hidden path="id"/>
	<input type="submit" />
</sf:form>
</body>
</html>