<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"">
	<title>Estudiante</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>
	<%@ include file="/WEB-INF/jsp/includes/body.jsp"%>
	<section class="midBot bgc1 p-5">
		<div class="container">
			<div class="row">
				<div class="col text-center bl">
					<h1>Listado de estudiantes</h1>
				</div>
			</div>
			<a class="btn btn-success" href="/java-people/EstudianteController?accion=form">Crear</a>
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Rut</th>
					<th>DigitoV</th>
					<th>Genero</th>
					<th>Telefono</th>
					<th>Curso</th>
					<th>Consultar</th>
					<th>Agregar</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="alumno" items="${estudiantes}">
				<tr>
					<td><c:out value="${estudiante.id}" /></td>
					<td><c:out value="${estudiante.nombre}" /></td>
					<td><c:out value="${estudiante.rut}" /></td>
					<td><c:out value="${estudiante.digitoV}" /></td>
					<td><c:out value="${estudiante.genero}" /></td>
					<td><c:out value="${estudiante.telefono}" /></td>
					<td><c:out value="${estudiante.curso}" /></td>
					<td>
						<a href="${pageContext.request.contextPath}/CalificacionController?accion=editar&amp;id=${calificacion.numeroEvaluacion}">Consultar</a> <%-- contextPath en este caso es /java-people --%>
						<a href="${pageContext.request.contextPath}/CalificacionController?accion=form&amp;id=${calificacion.numeroEvaluacion}">Agregar nota</a> <%-- contextPath en este caso es /java-people --%>
					</td>
				</tr>
			</c:forEach>			
			</tbody>
		</table>
		<a href="/java-people/index.jsp" class="btn btn-primary">volver</a>
		</div>
	</section>
	<%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
	<script 
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"crossorigin="anonymous">
	</script>

</body>
</html>