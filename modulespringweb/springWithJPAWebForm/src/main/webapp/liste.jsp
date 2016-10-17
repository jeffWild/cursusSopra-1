<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<form action="Index" method="post" onsubmit="return confirm('etes vous sur?');" >
		<input type="hidden" name="produitID" value="${p.id}" />
		<input type="submit" value="supprimer" />
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