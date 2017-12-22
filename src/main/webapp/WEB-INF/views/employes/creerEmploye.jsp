<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - App</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-4.0.0-beta.2-dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<h1 class="well">Nouveau collaborateur</h1>
		<div class="col-lg-12 well">
			<div class="row">
				<form method="post" action="#">
					<div class="form-group">
						<label>Matricule</label> <input type="text" required
							name="matricule" class="form-control">
					</div>
					<div class="form-group">
						<label for="entreprise">Entreprise</label> 
						<select id="entreprise" name="entreprise_id">
							<c:forEach var="entreprise" items="${entreprise}">
								<option value="${entreprise.id}">${entreprise.denomination}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="profil">Profil</label>
						<select id="profil" name="profil_id">
						<c:forEach var="profil" items="${profil}">
							<option value="${profil.id}">${profil.code}</option>
						</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="grade">Grade</label> 
						<select id="grade" name="grade_id">
						<c:forEach var="grade" items="${grade}">
							<option value="${grade.id}">${grade.code}</option>
						</c:forEach>
						</select>
					</div>
					<div>
						<button type="submit" class="btn btn-lg btn-info">Créer</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>