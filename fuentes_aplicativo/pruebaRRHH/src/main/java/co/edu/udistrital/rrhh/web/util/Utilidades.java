package co.edu.udistrital.rrhh.web.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades {


	public static String doubleFormated(Double number){
		DecimalFormat df = new DecimalFormat("$ ###,###,###.##");
		
		return df.format(number);
	}

	public static String dateFormated(Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
		if(fecha != null)
			return sdf.format(fecha);
		return "";
	}	

	public static String dateFormat(Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");  
		if(fecha != null)
			return sdf.format(fecha);
		return "";
	}


}
