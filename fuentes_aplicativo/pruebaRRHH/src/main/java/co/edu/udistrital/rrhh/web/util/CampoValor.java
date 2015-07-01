package co.edu.udistrital.rrhh.web.util;

import java.io.Serializable;

public class CampoValor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombreCampo;
	private String nombreTabla;
	
	public CampoValor() {
		// TODO Auto-generated constructor stub
	}

	public CampoValor(String nombreCampo, String nombreTabla) {
		super();
		this.nombreCampo = nombreCampo;
		this.nombreTabla = nombreTabla;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	
	

	
	
}