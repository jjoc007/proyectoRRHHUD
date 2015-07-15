package co.edu.udistrital.rrhh.web.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	public static String dateFormatedToFile(Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
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

	public static Calendar periodoLiquidacion(){
		
		//Recuperar de la tabla proceso el valor de PERIODO_LIQUIDACION = 1
		Calendar periodoLiq =  Calendar.getInstance();
		
		periodoLiq.set(Calendar.DAY_OF_MONTH, 1);
		periodoLiq.set(Calendar.MONTH, 1);
		periodoLiq.set(Calendar.YEAR, 2015);
		periodoLiq.set(Calendar.HOUR_OF_DAY, 0);
		periodoLiq.set(Calendar.MINUTE, 0);
		periodoLiq.set(Calendar.SECOND, 0);
		
		return periodoLiq;
	}

}
