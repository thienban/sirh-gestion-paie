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

	<h1>LISTE DES BULLETINS</h1>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th scope="col">Date/Heure création</th>
				<th scope="col">Periode</th>
				<th scope="col">Matricule</th>
				<th scope="col">Salaire brut</th>
				<th scope="col">Salaire net</th>
				<th scope="col">Net imposable</th>
				<th scope="col">Net a payer</th>
				<th scope="col">Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bul" items="${bulletins}">
				<tr>
					<td>${bul.dateCreation}</td>
					<td>${bul.periode}</td>
					<td>${bul.remunerationEmploye.matricule}</td>
					<td>${bul.value.salairebrut}</td>
					<td>${bul.value.salairenet}</td>
					<td>${bul.value.netimposable}</td>
					<td>${bul.value.netapayer}</td>
					<td>Visualiser</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>