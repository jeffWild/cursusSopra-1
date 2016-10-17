<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>liste des interventions</title>
</head>
<body>
<h2>liste des interventions</h2>
<a href="Index">non trié</a>,<a href="Index?tri=date">trié par date</a> 
<table border="1">
<tr>
<th>intervenant</th>
<th>lieu</th>
<th>noMateriel</th>
<th>description</th>
<th>date</th>
<th>actions</th>
</tr>
<c:forEach items="${interventions}" var="intervention">
<tr>
	<td>${intervention.intervenant}</td>
	<td>${intervention.lieu}</td>
	<td>${intervention.noMateriel}</td>
	<td>${intervention.description}</td>
	<td>${intervention.dateProgrammee}</td>
	<td>
		<form action="Intervention" method="get">
			<input type="hidden" name="interventionID" value="${intervention.id}" />
			<input type="submit" value="editer" />
		</form>
		<form action="Index" method="post" onsubmit="return confirm('etes vous sur?');">
			<input type="hidden" name="interventionID" value="${intervention.id}" />
			<input type="submit" value="supprimer" />
		</form>
	</td>
</tr>
</c:forEach>
</table>
<form action="Intervention" method="get">
	<input type="submit" value="creer" />
</form>
</body>
</html>