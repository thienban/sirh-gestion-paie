<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Créer Employe</h1>


<form:form method="post" modelAttribute="employeF">
	<table>
		<tr>
			<td>Matricule</td>
			<td><form:input path="matricule" /></td>
		</tr>
		<tr>
			<td>Entreprise</td>
			<td><form:input path="entreprise" /></td>
		</tr>
		<tr>
			<td>Profil</td>
			<td><form:input path="profil" /></td>
		</tr>
		<tr>
			<td>Grade</td>
			<td><form:input path="grade" /></td>
		</tr>
	</table>
</form:form>