<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>liste des produits</title>
</head>
<body>
<h2>liste des produits</h2>
<table border="1">
<tr><th>nom</th><th>prix</th><th>poids</th><th>actions</th></tr>
<c:forEach items="${produits}" var="p">
<tr>
	<td><c:out value="${p.nom}" /></td>
	<td><c:out value="${p.prix}" /></td>
	<td><c:out value="${p.poids}" /></td>
	<td>
	<form action="Produit" method="get">
		<input type="hidden" name="produitID" value="${p.id}" />
		<input type="submit" value="editer" />
	</form>
	</td>
</tr>
</c:forEach>
</table>
<form action="Produit" method="get">
	<input type="submit" value="creer produit" />
</form>
</body>
</html>