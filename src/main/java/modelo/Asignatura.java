package modelo;

public class Asignatura {
	
	private int id;
	private String nombreAsignatura;
	
	
	public Asignatura() {
		
	}
	
	public Asignatura(int id, String nombreAsignatura) {
		
		this.id = id;
		this.nombreAsignatura = nombreAsignatura;
	}
	
	public Asignatura(String nombreAsignatura2) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreAsignatura() {
		return nombreAsignatura;
	}
	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}
	

}
