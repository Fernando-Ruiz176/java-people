<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Form. Asignatura</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body bgcolor="beige">
	<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>
	<section class="midBot bgc1 p-5">
		<div class="container">
			<div class="row">
				<div class="col text-center bl">
					<h1>REGISTRO DE ASIGNATURAS</h1>
				</div>
			</div>
			<form method="POST" action="/JAVA-PEOPLE/AsignaturaController">
				<input type="hidden" name="id" value="${asignatura.id}" />
				<div class="mb-3">
		  			<label for="nombre" class="form-label">Nombre :</label>
		  			<input type="text" class="form-control" value="${asignatura.nombreAsignatura}" id="nombre" name="nombre" />
				</div>
				<button class="btn btn-primary" type="submit">Mostrar</button>
				<button class="btn btn-primary" type="submit">Agregar</button>
				<a href="/webapp/index.jsp" class="btn btn-primary">volver</a>
			</form>
		</div>
	</section>
	<%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"crossorigin="anonymous"></script>
</body>
</html>