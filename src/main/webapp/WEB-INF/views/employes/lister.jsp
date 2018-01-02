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

	<h1>LISTE DES COLLABORATEURS</h1>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th scope="col">Matricule</th>
				<th scope="col">Grade</th>
				<th scope="col">Date Creation</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${employes}">
				<tr>
					<td>${emp.matricule}</td>
					<td>${emp.grade.code}</td>
					<td>${emp.dateCreation}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>