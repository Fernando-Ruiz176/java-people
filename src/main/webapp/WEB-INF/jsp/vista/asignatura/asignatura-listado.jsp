<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Asignatura</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>
	<section class="midBot bgc1 p-5">
		<div class="container">
			<div class="row">
				<div class="col text-center bl">
					<h1>Listado de asignaturas</h1>
				</div>
			</div>
			<a class="btn btn-success" href="/cft-web/EstudianteController?accion=form">Crear</a>
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="alumno" items="${alumnos}">
				<tr>
					<td><c:out value="${asignatura.id}" /></td>
					<td><c:out value="${asignatura.nombreAsignatura}" /></td>
					<td>
						<a href="${pageContext.request.contextPath}/EstudianteController?accion=editar&amp;id=${asignatura.id}">Editar</a> <%-- contextPath en este caso es /java-people --%>
						<a href="${pageContext.request.contextPath}/EstudianteController?accion=eliminar&amp;id=${asignatura.id}">Eliminar</a> <%-- contextPath en este caso es /java-people --%>
					</td>
				</tr>
			</c:forEach>			
			</tbody>
		</table>
		</div>
		<a href="/java-people/index.jsp" class="btn btn-primary">volver</a>
	</section>
	<%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
	<script 
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"crossorigin="anonymous">
	</script>
</body>
</html>