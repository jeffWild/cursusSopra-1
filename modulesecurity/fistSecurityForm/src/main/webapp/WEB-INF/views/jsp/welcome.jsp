<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bonjour</title>
<s:url value="/resources/css/bonjour.css" var="coreStyle" />
<link href="${coreStyle}" rel="stylesheet" />
</head>
<body>
<h2>${message}</h2>
<c:url value='/logout'  var="logoutUrl" />
<form:form action="${logoutUrl}" method="post">
	<input type="submit" value="se deloguer" />
</form:form>

<hr />

<c:url value='/public/register'  var="registerUrl" />
<form:form action="${registerUrl}" method="post">
	nom: <input name="name" type="text" /><br />
	mot de passe: <input name="password" type="text" /><br />
	<input type="submit" value="creer utilisateur" />
</form:form>

</body>
</html>