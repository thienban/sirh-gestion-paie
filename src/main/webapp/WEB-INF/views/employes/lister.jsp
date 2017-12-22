<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>ICI BIENTOT LISTER !!!!</h1>
<ul>
<c:forEach var="emp" items="${employes}">
	<li>${emp.matricule}</li>

</c:forEach>
</ul>
</body>
</html>