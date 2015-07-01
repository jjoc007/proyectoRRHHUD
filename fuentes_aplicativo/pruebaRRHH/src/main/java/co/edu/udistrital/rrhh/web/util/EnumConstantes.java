package co.edu.udistrital.rrhh.web.util;

public enum EnumConstantes {
	

	TIPO_CONCEPTO_DEVENGO(0, ""),
	TIPO_CONCEPTO_DEDUCCION(1, "");	
	
	private EnumConstantes(int valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	private int valor;
	private String descripcion;
	
	public static EnumConstantes get(int valor) {
		for (EnumConstantes v : values()) {
			if (v.valor == valor) {
				return v;
			}
		}
		return null;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
