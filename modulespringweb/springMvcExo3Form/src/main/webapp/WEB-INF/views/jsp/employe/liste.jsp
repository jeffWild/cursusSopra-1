<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>liste des messages</title>
<s:url value="/resources/css/bootstrap.css" var="bootstrapStyle" />
<s:url value="/resources/css/employee.css" var="coreStyle" />
<s:url value="/resources/js/jquery-2.1.4.js" var="jqueryjs" />
<s:url value="/resources/js/bootstrap.js" var="bootstrapjs" />

<s:url value="/employe/add" var="addEmploye" />

<link href="${bootstrapStyle}" rel="stylesheet" />
<link href="${coreStyle}" rel="stylesheet" />
<script type="text/javascript" src="${jqueryjs}"></script>
<script type="text/javascript" src="${bootstrapjs}"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Employe MVC</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${addEmploye}">ajouter Employe</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="container">
	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>


	<h1>liste des Employe</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Email</th>
				<th>Poste</th>
				<th>Salaire</th>
				<th>Departement</th>
				<th>Actions</th>
			</tr>
		</thead>
		<c:forEach items="${employes}" var="e" >
		<tr>
			<s:url value="/employe/filter/${e.departement.id}" var="filtreDepartement" />
			<td>${e.nom}</td>
			<td>${e.email}</td>
			<td>${e.poste}</td>
			<td>${e.salaire}</td>
			<td><a href="${filtreDepartement}" >${e.departement.nom}</a></td>
			<td>
				<s:url value="/employe/edit/${e.id}" var="editEmploye" />
				<s:url value="/employe/delete/{eid}" var="removeEmploye">
					<s:param name="eid" value="${e.id}" />
				</s:url>
				<button class="btn btn-primary" onclick="location.href='${editEmploye}'">Editer</button>
				<button class="btn btn-danger" onclick="location.href='${removeEmploye}'">Supprimer</button>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

</body>
</html>