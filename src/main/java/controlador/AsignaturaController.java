package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Asignatura;
import modelo.Calificacion;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import daoService.AsignaturaDAO;
import daoService.AsignaturaDAOImp;
import daoService.CalificacionDAO;




public class AsignaturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private AsignaturaDAO asignaturaDAO;
	private CalificacionDAO calificacionDAO;
	
	
	 public AsignaturaController() {
		 super();
	 }
	 
	 @Override
		public void init() throws ServletException {
			super.init();
			this.asignaturaDAO = new AsignaturaDAOImp();
		}


		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
			String vistaJSP = "";
			switch (accion) {
			
			case "eliminar":
				try {
					int asignaturaId = Integer.parseInt(request.getParameter("id"));
					asignaturaDAO.eliminarAsignatura(asignaturaId);
					response.sendRedirect("/java-people/AsignaturaController?accion=listar");
				}catch(SQLException sql) {
					sql.printStackTrace();
					response.sendError(404);
				}catch(NamingException ne) {
					ne.printStackTrace();
					response.sendError(500);
				}
				break;
				
				
			case "editar":
				try {
					int asignaturaId = Integer.parseInt(request.getParameter("id"));
					Asignatura asignatura = asignaturaDAO.findAsignaturaById(asignaturaId);
					request.setAttribute("asignatura", asignatura);
					vistaJSP ="WEB-INF/jsp/vista/asignatura/asignatura-form.jsp";
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
				vistaJSP ="WEB-INF/jsp/vista/asignatura/asignatura-form.jsp";
				request.getRequestDispatcher(vistaJSP);
				try {
					List<Calificacion> calificacion = calificacionDAO.findAllCalificacion();
				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
				request.getRequestDispatcher(vistaJSP).forward(request, response);
				break;
				
				
				
			case "listar":
				try {
					List<Asignatura> asignaturas = asignaturaDAO.findAllAsignatura();
					request.setAttribute("asignaturas", asignaturas);
					vistaJSP = "/WEB-INF/jsp/vista/asignatura/asignatura-listado.jsp";
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
			
			String nombreAsignatura 	= request.getParameter("nombreAsignatura");
			
			if(id == 0) {
				// crear la asignatura
				Asignatura asignaturaNueva = new Asignatura(nombreAsignatura);
				try {
					asignaturaDAO.crearAsignatura(asignaturaNueva);
					response.sendRedirect("/java-people/AsignaturaController?accion=listar");
				} catch (SQLException | NamingException e) {				
					e.printStackTrace();
					response.sendError(500);
				}
			} else {
				// editar
				Asignatura asignaturaAEditar = new Asignatura(id, nombreAsignatura);
				try {
					asignaturaDAO.editarAsigantura(asignaturaAEditar);
					response.sendRedirect("/java-people/AsignaturaController?accion=listar");
				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
				}
			} 
		}

}
