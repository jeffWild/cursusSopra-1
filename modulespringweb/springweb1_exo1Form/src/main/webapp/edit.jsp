<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edition d'intervention</title>
</head>
<body>
<h2>edition d'intervention</h2>
<form action="Intervention" method="post">
	<input type="hidden" name="interventionID" value="${requestScope.intervention.id}"/>
	<label for="interventionIntervenant" >intervenant</label>
	<input type="text" name="interventionIntervenant" value="${requestScope.intervention.intervenant}" /><br />
	<label for="interventionLieu" >lieu</label>
	<input type="text" name="interventionLieu" value="${requestScope.intervention.lieu}" /><br />
		<label for="interventionNoMateriel" >materiel</label>
	<input type="text" name="interventionNoMateriel" value="${requestScope.intervention.noMateriel}" /><br />
	<label for="interventionDescription" >description</label>
	<textarea name="interventionDescription" rows="5" cols="50">${requestScope.intervention.description}</textarea><br />
	<label for="interventionDateProgramme" >date</label>
	<input type="text" name="interventionDateProgrammee" value="${requestScope.intervention.dateProgrammee}" /><br />
	<input type="submit">	
</form>
</body>
</html>