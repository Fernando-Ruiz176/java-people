package validator;

import java.util.HashMap;
import java.util.Map;

import modelo.Calificacion;

public class CalificacionValidator {
	
	private String numeroEvaluacion;
	private String nota;
	
	private int numeroEvaluacionValidado;
	private String notaValidado;
	
	private Map<String, String> errores = new HashMap<String, String>();
	
	public CalificacionValidator(String numeroEvaluacion, String nota) {
		this.numeroEvaluacion = numeroEvaluacion;
		this.nota = nota;
	}
	
	public Calificacion makeObject() {
		if( isValid() ) {
			return new Calificacion(numeroEvaluacionValidado, null, notaValidado);
		} else {
			return null;
		}
	}
	
	public boolean isValid() {
		boolean isNumeroEvaluacionValid				=		isNumeroEvaluacionValid();
		boolean isNotaValid							=		isNotaValid();
		
		if( isNumeroEvaluacionValid && isNotaValid ) {
			return true;
			
		} else {
			return false;
		}
	}
	
	public boolean isNumeroEvaluacionValid() {
		try {
			numeroEvaluacionValidado = Integer.parseInt(numeroEvaluacion);
		} catch (Exception e) {
			numeroEvaluacionValidado = 0;
		}
		return true;
	}
	
	public boolean isNotaValid() {
		if( nota.length() >  1) {
			notaValidado = nota;
			return true;
		} else {
			errores.put("nota", "La nota es demasiado corta:");
			return false;
		}
	}
	
	public Map<String, String> getErrores() {
		return errores;
	}

	public String getNumeroEvaluacion() {
		return numeroEvaluacion;
	}

	public String getNota() {
		return nota;
	}
	

}
