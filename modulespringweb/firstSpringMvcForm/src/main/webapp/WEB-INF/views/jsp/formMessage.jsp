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
<s:url value="/message/add" var="addTweet" />

<link href="${bootstrapStyle}" rel="stylesheet" />
<link href="${coreStyle}" rel="stylesheet" />
</head>
<body>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Tweet MVC</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${addTweet}">ajouter tweet</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="container">
	<c:choose>
		<c:when test="${message.id == 0}">
			<h1>creation d'un tweet</h1>
		</c:when>
		<c:otherwise>
			<h1>edition d'un tweet</h1>
		</c:otherwise>
	</c:choose>
	<s:url value="/message/save" var="saveMessage" />
	<br />
	<sf:form class="form-horizontal"
			 method="post"
			 action="${saveMessage}"
			 commandName="message">
		<sf:hidden path="id" />
		<div class="form-group">
			<label class="col-sm-2 control-label">Titre</label>
			<div class="col-sm-10">
				<sf:input path="titre" type="text" class="form-control" id="titre"/>
				<sf:errors path="titre" class="control-label"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Corps</label>
			<div class="col-sm-10">
				<sf:textarea path="corps" class="form-control" id="corps"/>
				<sf:errors path="corps" class="control-label"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<sf:input path="email" type="text" class="form-control" id="email"/>
				<sf:errors path="email" class="control-label"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Date</label>
			<div class="col-sm-10">
				<sf:input path="dateMessage" type="text" class="form-control" id="dateMessage"/>
				<sf:errors path="dateMessage" class="control-label"/>
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