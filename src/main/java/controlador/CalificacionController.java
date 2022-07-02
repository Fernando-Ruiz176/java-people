package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Calificacion;
import modelo.Estudiante;
import validator.CalificacionValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import daoService.AsignaturaDAO;
import daoService.CalificacionDAO;
import daoService.CalificacionDAOImp;
import daoService.EstudianteDAO;
import daoService.EstudianteDAOImp;

public class CalificacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EstudianteDAO estudianteDAO;
	private AsignaturaDAO asignaturaDAO;
	private CalificacionDAO calificacionDAO;

    public CalificacionController() {
        
    }
    
    @Override
	public void init() throws ServletException {
		super.init();
		this.estudianteDAO = new EstudianteDAOImp();
		this.calificacionDAO = new CalificacionDAOImp( this.estudianteDAO );
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String vistaJSP = "";
		switch (accion) {
		
			case "eliminar":
				try {
					int calificacionNumeroEvaluacion = Integer.parseInt(request.getParameter("numeroEvaluacion") );
					calificacionDAO.eliminarCalificacion(calificacionNumeroEvaluacion );
					response.sendRedirect("/java-people/CalificacionController?accion=listar");
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					response.sendError(500);
				} catch (NamingException ne) {
					ne.printStackTrace();
					response.sendError(500);
				}				
				break;
				
				
				
				
			case "editar":
				try {
					int calificacionNumeroEvaluacion = Integer.parseInt( request.getParameter("numeroEvaluacion") );
					List<Estudiante> estudiantes = estudianteDAO.findAllEstudiante();					
					request.setAttribute("estudiantes", estudiantes);
					Calificacion calificacion = calificacionDAO.findCalificacionById(calificacionNumeroEvaluacion);
					request.setAttribute("calificacion", calificacion);
					vistaJSP = "/WEB-INF/jsp/vista/calificacion/calificacion-form.jsp";
					request
						.getRequestDispatcher(vistaJSP)
						.forward(request, response)
					;
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					response.sendError(500);
				} catch (NamingException ne) {
					ne.printStackTrace();
					response.sendError(500);
				}
				break;
				
				
			case "form":
				vistaJSP = "/WEB-INF/jsp/vista/calificacion/calificacion-form.jsp";
				List<Estudiante> estudiantes = null;
				try {
					estudiantes = estudianteDAO.findAllEstudiante();
				} catch(Exception e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
				request.setAttribute("estudiantes", estudiantes);
				request
					.getRequestDispatcher(vistaJSP)
					.forward(request, response)
				;
				break;
				
				
			case "listar":
				try {
					List<Calificacion> calificaciones = calificacionDAO.findAllCalificacion();
					request.setAttribute("calificaciones", calificaciones);
					vistaJSP = "/WEB-INF/jsp/vista/calificacion/calificacion-listado.jsp";
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
	
		String numeroEvaluacionStr 				= request.getParameter("numeroEvaluacion");
		String notaStr 							= request.getParameter("nota");
		
		CalificacionValidator validador 		= new CalificacionValidator(numeroEvaluacionStr , notaStr);
		Calificacion calificacion				= validador.makeObject();
		
		Estudiante estudiante 	= null;
		try {
			int estudianteId 	= Integer.parseInt( request.getParameter("estudiante_id") );
			estudiante 		= estudianteDAO.findEstudianteById(estudianteId);			
			
			List<Estudiante> estudiantes = estudianteDAO.findAllEstudiante();
			request.setAttribute("estudiantes", estudiantes);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			response.sendError(500);
			return;
		}
		
		// si la calificacion es null, es porque se produjeron errores de validación 
		if( calificacion == null ) {
			request.setAttribute("validador", validador);
			String jspForm = "/WEB-INF/jsp/vista/calificacion/calificacion-form.jsp";
			request.getRequestDispatcher(jspForm).forward(request, response);
			return;
		}		
		
		calificacion.setEstudiante(estudiante);				
		if(calificacion.getNumeroEvaluacion() == 0) {
			// crear la calificacion 
			try {
				calificacionDAO.crearCalificacion(calificacion);
				response.sendRedirect("/java-people/CalificacionController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			// editar			
			try {
				calificacionDAO.editarCalificacion(calificacion);
				response.sendRedirect("/java-people/CalificacionController?accion=listar");
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
		
	}

}
