package modelo;

public class Calificacion {
	
	private int numeroEvaluacion;
	private String nota;
	private Estudiante estudiante;
	private Asignatura asignatura;
	
	
	public Calificacion() {
		super();
	}
	
	public Calificacion(String nota, Estudiante estudiante, Asignatura asignatura) {
		super();
		this.nota = nota;
		this.estudiante = estudiante;
		this.asignatura = asignatura;
	}

	
	public Calificacion(int numeroEvaluacion, String nota, Estudiante estudiante, Asignatura asignatura) {
		super();
		this.numeroEvaluacion = numeroEvaluacion;
		this.nota = nota;
		this.estudiante = estudiante;
		this.asignatura = asignatura;
	}
	
	public Calificacion(int numeroEvaluacionValidado, Object object, String notaValidado) {
		// TODO Auto-generated constructor stub
	}

	public int getNumeroEvaluacion() {
		return numeroEvaluacion;
	}
	public void setNumeroEvaluacion(int numeroEvaluacion) {
		this.numeroEvaluacion = numeroEvaluacion;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

}
