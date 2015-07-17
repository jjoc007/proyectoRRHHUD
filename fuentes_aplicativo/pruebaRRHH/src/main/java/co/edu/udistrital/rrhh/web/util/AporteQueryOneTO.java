package co.edu.udistrital.rrhh.web.util;

import java.io.Serializable;
import java.util.Date;

public class AporteQueryOneTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double valor;
	String entidad;
	Date periodo;
	Long cuenta;
	
	public AporteQueryOneTO() {
		// TODO Auto-generated constructor stub
	}

	public AporteQueryOneTO(Double valor, String entidad, Date periodo,
			Long cuenta) {
		super();
		this.valor = valor;
		this.entidad = entidad;
		this.periodo = periodo;
		this.cuenta = cuenta;
	}



	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}

	public Long getCuenta() {
		return cuenta;
	}

	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}

	public String getFormatedValor(){
		
		if(this.valor!= null){
			return Utilidades.doubleFormated(valor);
		}
		
		return "$ 0";
		
	}
	
	public String getFormatedPeriodo(){
		
		if(this.periodo!= null){
			
			return Utilidades.dateFormat(periodo);
		}
		return "";
		
	}
	
}
