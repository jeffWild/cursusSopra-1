<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edition de produit</title>
</head>
<body>
<h2>edition de produit</h2>
<form action="Produit" method="post">
	<input type="hidden" name="produitID" value="${requestScope.produit.id}">
	<label for="produitNom" >nom</label>
	<input type="text" name="produitNom" value="${requestScope.produit.nom}" /><br />
	<label for="produitPrix" >prix</label>
	<input type="text" name="produitPrix" value="${requestScope.produit.prix}" /><br />
	<label for="produitPoids" >poids</label>
	<input type="text" name="produitPoids" value="${requestScope.produit.poids}" /><br />
	<input type="submit">	
</form>
</body>
</html>