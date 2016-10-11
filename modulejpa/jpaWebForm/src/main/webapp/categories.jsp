<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des categories</title>
</head>
<body>
<table border="1">
	<tr><th>libelle</th><th>posts</th></tr>
	<c:forEach items="${categories}" var="c" >
	<tr>
		<td>
			<c:out value="${c.libelle}" />
		</td>
		<td>
			<c:forEach items="${c.posts}" var="p">
				<c:out value="${p.titre}" />,
			</c:forEach>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>