package co.edu.udistrital.rrhh.web.util;

import java.io.Serializable;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

public class NominaException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stack;

	static Logger logger = Logger.getLogger(NominaException.class.getName());

	
	/**
	 * Procesa la excepcion para retornar a las capas superiores (capa de
	 * Interaccion) unicamente el Stack de la Excepcion original.
	 * 
	 * @param message = Define el mensaje dado para la app
	 */	public NominaException(String message) {
		super(message);
		this.writeTrace();
	}


	/**
	 * Procesa la excepcion para retornar a las capas superiores (capa de
	 * Interaccion) unicamente el Stack de la Excepcion original.
	 * 
	 * @param message = Define el mensaje dado para la app
	 * @param cause = Causa de la Excepcion
	 */
	public NominaException(String message, Exception cause) {
		super(message, cause);
		this.writeTrace();
	}

	/**
	 * Procesa la excepcion para retornar a las capas superiores (capa de
	 * Interaccion) unicamente el Stack de la Excepcion original.
	 * 
	 * @param cause = Causa de la Excepcion
	 */
	public NominaException(Throwable cause) {
		this.writeTrace();
	}

	private void writeTrace() {
		logger.error("getMessage : " + ExceptionUtils.getMessage((Exception) this));
		logger.error("getRootCauseMessage : " + ExceptionUtils.getRootCauseMessage((Exception) this));
		logger.error("getThrowableCount : " + ExceptionUtils.getThrowableCount((Exception) this));		
		
		StackTraceElement origen[] = ((Exception) this).getStackTrace();
		stack = "";
		for (int i = 0; i < origen.length; i++) {
			stack += "Class = " + origen[i].getClassName() + " Method = "
					+ origen[i].getMethodName() + " LineNumber = "
					+ origen[i].getLineNumber() + "\n";
		}
		logger.error(this.stack);
		// factory = new ViewExpiredExceptionExceptionHandlerFactory(factory);
		// handler = new
		// ViewExpiredExceptionExceptionHandler(factory.getExceptionHandler());
		// handler.handle();
	}

}
