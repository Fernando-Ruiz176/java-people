<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark container-fluid bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">JAVA-PEOPLE</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">     
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
           Estudiantes
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="/java-people/EstudianteController?accion=form">Crear</a></li>
            <li><a class="dropdown-item" href="/java-people/EstudianteController?accion=listar">Ver</a></li>                   
          </ul>          
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Asignaturas
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="/java-people/AsignaturaController?accion=listar">Ver</a></li>            
          </ul>          
        </li>
      </ul>
    </div>
  </div>
</nav>