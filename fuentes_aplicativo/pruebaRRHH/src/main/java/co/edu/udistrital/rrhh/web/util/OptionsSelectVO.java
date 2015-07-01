package co.edu.udistrital.rrhh.web.util;

import java.math.BigDecimal;


public class OptionsSelectVO {

	private BigDecimal value;
	private String label;

	public OptionsSelectVO() {
		// TODO Auto-generated constructor stub
	}

	public OptionsSelectVO(BigDecimal value, String label) {
		super();
		this.value = value;
		this.label = label;
	}


	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	

}
