package daoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Asignatura;
import modelo.Calificacion;
import modelo.Estudiante;

public class CalificacionDAOImp implements CalificacionDAO{

	private EstudianteDAO estudianteDAO;
	private AsignaturaDAO asignaturaDAO;


	@Override
	public List<Calificacion> findAllCalificacion() throws SQLException, NamingException {
		try(
				Connection conn = DbUtils.getConexion();
				Statement declaracion = conn.createStatement();
			) {
			ResultSet rs = declaracion.executeQuery("SELECT * FROM calificacion");
			List<Calificacion> calificaciones = new ArrayList<>();
			while(rs.next()) {
				int numeroEvaluacion = rs.getInt("numero_evaluacion");
				String nota = rs.getString("nota");
				int estudianteId = rs.getInt("estudiante_id");
				Estudiante estudiante = estudianteDAO.findEstudianteById(estudianteId);
				int asignaturaId = rs.getInt("asignatura_id");
				Asignatura asignatura = asignaturaDAO.findAsignaturaById(asignaturaId);
				//INSTANCIAR EL OBJETO
				Calificacion calificacion = new Calificacion( nota, estudiante, asignatura);
				//AGREGAR A LA LISTA
				calificaciones.add(calificacion);
				
				}
			return calificaciones;
			}
	}

	@Override
	public Calificacion findCalificacionById(int numeroEvaluacionId) throws SQLException, NamingException {
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM calificacion WHERE numero_evaluacion = ?");
			) {
			
				declaracion.setInt(1, numeroEvaluacionId);
				ResultSet rs = declaracion.executeQuery();
				
				if(rs.next()) {
					int numeroEvaluacion = rs.getInt("numero_evaluacion");
					String nota = rs.getString("nota");
				    int estudianteId = rs.getInt("estudiante_id");
					Estudiante estudiante = estudianteDAO.findEstudianteById(estudianteId);
					int asignaturaId = rs.getInt("asignatura_id");
					Asignatura asignatura = asignaturaDAO.findAsignaturaById(asignaturaId);
					return new Calificacion(numeroEvaluacion,nota,estudiante, asignatura);
				}else {
					return null;
				}
			}
	}
		
	@Override
	public void crearCalificacion(Calificacion calificacion) throws SQLException, NamingException {
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO calificacion(numero_evaluacion, nota, estudiante_id, asignatura_id) VALUES(?,?,?,?)");	
			){
				
				declaracion.setString(1, calificacion.getNota());
				declaracion.setInt(2, calificacion.getEstudiante().getId());
				declaracion.setInt(3, calificacion.getAsignatura().getId() );
				int calificacionInsertada = declaracion.executeUpdate();
				
		}
	}

	@Override
	public void editarCalificacion(Calificacion calificacion) throws SQLException, NamingException {
		String sql = "UPDATE calificacion"
				+" SET numero_evaluacion = ?, nota = ?, estudiante_id = ?, asignatura_id = ?"
				+" WHERE id = ?";
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO calificacion(numero_evaluacion, nota, estudiante_id, asignatura_id) VALUES(?,?,?,?)");	
			){
				
				declaracion.setString(1, calificacion.getNota());
				declaracion.setInt(2, calificacion.getEstudiante().getId());
				declaracion.setInt(3, calificacion.getAsignatura().getId() );
				declaracion.executeUpdate();
				
		}
		
	}

	@Override
	public void eliminarCalificacion(int numeroEvaluacion) throws SQLException, NamingException {
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM calificacion WHERE id = ?");	
			){
				declaracion.setInt(1,numeroEvaluacion);
				int calificacionEliminada = declaracion.executeUpdate();
			} 
	}
		
	public CalificacionDAOImp(AsignaturaDAO asignaturaDAO2, CalificacionDAO calificacionDAO) {
		// TODO Auto-generated constructor stub
	}

	public CalificacionDAOImp(EstudianteDAO estudianteDAO2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Calificacion findLastCreatedCalificacion() throws SQLException, NamingException {
		return null;
	}

	@Override
	public Calificacion findCalificacionByForeignId(int idAsignatura, int idEstudiante) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
