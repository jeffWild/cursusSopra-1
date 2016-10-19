<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edition Message</title>
<s:url value="/resources/css/bootstrap.css" var="bootstrapStyle" />
<s:url value="/resources/css/message.css" var="coreStyle" />
<s:url value="/employe/add" var="addEmploye" />

<link href="${bootstrapStyle}" rel="stylesheet" />
<link href="${coreStyle}" rel="stylesheet" />
</head>
<body>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Employe MVC</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${addEmploye}">ajouter employe</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="container">
	<c:choose>
		<c:when test="${employe.id == 0}">
			<h1>creation d'un employe</h1>
		</c:when>
		<c:otherwise>
			<h1>edition d'un employe</h1>
		</c:otherwise>
	</c:choose>
	<s:url value="/employe/save" var="saveEmploye" />
	<br />
	<sf:form class="form-horizontal"
			 method="post"
			 action="${saveEmploye}"
			 commandName="employe">
		<sf:hidden path="id" />
		<div class="form-group">
			<label class="col-sm-2 control-label">Nom</label>
			<div class="col-sm-10">
				<sf:input path="nom" type="text" class="form-control" id="nom"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<sf:input path="email" type="text" class="form-control" id="email"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Salaire</label>
			<div class="col-sm-10">
				<sf:input path="salaire" type="text" class="form-control" id="salaire"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Poste</label>
			<div class="col-sm-10">
				<sf:input path="poste" type="text" class="form-control" id="poste"/>
			</div>
		</div>
<!-- 		<div class="form-group">
			<label class="col-sm-2 control-label">Departement</label>
			<div class="col-sm-10">
				<sf:input path="departementID" type="text" class="form-control" id="departementID"/>
			</div>
		</div>
 -->	
 		<div class="form-group">
			<label class="col-sm-2 control-label">Departement</label>
			<div class="col-sm-10">
<!-- 				<sf:select path="departementID"
						   type="text"
						   class="form-control"
						    id="departementID"
						    items="${departements}"
						    itemLabel="nom"
						    itemValue="id"
						    >
				</sf:select> -->
					<sf:select path="departementID"
						   type="text"
						   class="form-control"
						    id="departementID"
						    >
					<sf:option value="0">aucun</sf:option>
					<sf:options items="${departements}"
								itemLabel="nom"
						    	itemValue="id"/>
				</sf:select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-right">Sauver</button>
			</div>
		</div>
	</sf:form>
</div>

</body>
</html>