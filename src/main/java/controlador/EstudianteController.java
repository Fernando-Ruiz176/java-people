package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import modelo.Calificacion;
import modelo.Estudiante;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import daoService.CalificacionDAO;
import daoService.EstudianteDAO;
import daoService.EstudianteDAOImp;


public class EstudianteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EstudianteDAO estudianteDAO;
	private CalificacionDAO calificacionDAO;
	
    public EstudianteController() {
    
    }
    
	@Override
	public void init() throws ServletException {
		super.init();
		this.estudianteDAO = new EstudianteDAOImp();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
		String vistaJSP = "";
		switch (accion) {
		
		case "eliminar":
			try {
			int estudianteID = Integer.parseInt(request.getParameter("id"));
			estudianteDAO.eliminarEstudiante(estudianteID);
			response.sendRedirect("/java-people/EstudianteController?accion=listar");
		}catch(SQLException sql) {
			sql.printStackTrace();
			response.sendError(404);
		}catch(NamingException sql1) {
			sql1.printStackTrace();
			response.sendError(500);
		}
		break;
			
		case "editar":
		try {
			int estudianteId = Integer.parseInt(request.getParameter("id"));
			Estudiante estudiante = estudianteDAO.findEstudianteById(estudianteId);
			request.setAttribute("estudiante", estudiante);
			vistaJSP ="WEB-INF/jsp/vista/estudiante/estudiante-form.jsp";
			request.getRequestDispatcher(vistaJSP).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(404);
			
		} catch (NamingException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		break;
		
		case "form":
			vistaJSP ="WEB-INF/jsp/vista/estudiante/estudiante-form.jsp";
			request.getRequestDispatcher(vistaJSP).forward(request, response);
			break;
			
			
			
		case "listar":
			List<Estudiante> estudiantes = null;
			try {
				List<Estudiante> estudiante = estudianteDAO.findAllEstudiante();
				request.setAttribute("estudiantes", estudiantes);
				vistaJSP = "/WEB-INF/jsp/vista/estudiante/estudiante-listado.jsp";
				request
					.getRequestDispatcher(vistaJSP)
					.forward(request, response)
				;
			} catch (SQLException | NamingException e) {
				response.sendError(500);
			}
			break;
		default:
			response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = 0;
		try {
			id = Integer.parseInt( request.getParameter("id") );
		} catch (NumberFormatException e) {
			System.err.println("id se setea a 0 de manera automática.");
		}
		
		String nombre 	= request.getParameter("nombre");
		
		String curso	= request.getParameter("curso");
		int telefono = 0;
		int rut = 0;
		String digitoV	= request.getParameter("digitoV");
		String genero	= request.getParameter("genero");
		if(id == 0) {
			// crear el estudiante
			Estudiante estudianteNuevo = new Estudiante(nombre, rut, digitoV, genero, telefono, curso);
			try {
				estudianteDAO.crearEstudiante(estudianteNuevo);
				response.sendRedirect("/java-web/EstudianteController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			// editar
			Estudiante estudianteAEditar = new Estudiante(id, nombre, rut, digitoV, genero, telefono, curso);
			try {
				estudianteDAO.editarEstudiante(estudianteAEditar);
				response.sendRedirect("/java-web/EstudianteController?accion=listar");
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		} 	
		
		}
	}
