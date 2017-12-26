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
		<h1 class="well">Nouveau bulletin</h1>
		<div class="col-lg-12 well">
			<div class="row">
				<form method="post" action="#">
					<div class="form-group">
						<label for="periode">Periode</label> <select id="periode"
							name="periode_id">
							<c:forEach var="periode" items="${periodes}">
								<option value="${periode.id}">${periode.dateDebut} - ${periode.dateFin}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="employe">Matricule</label> <select id="employe"
							name="employe_id">
							<c:forEach var="employe" items="${employes}">
								<option value="${employe.id}">${employe.matricule}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>Prime Exceptionnel</label> <input type="text" required
							name="prime" class="form-control">
					</div>
				</form>
			</div>
			<div>
				<button type="submit" class="btn btn-lg btn-info">Créer</button>
			</div>
		</div>
	</div>
</body>
</html>