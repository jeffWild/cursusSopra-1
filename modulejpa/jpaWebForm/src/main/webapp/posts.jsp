<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des posts</title>
</head>
<body>
<table border="1">
	<tr><th>titre</th><th>corps</th><th>categorie</th></tr>
	<c:forEach items="${posts}" var="p" >
	<tr>
		<td>
			<c:out value="${p.titre}" />
		</td>
		<td>
			<c:out value="${p.corps}" />
		</td>
		<td>
			<c:out value="${p.categorie.libelle}" />
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>