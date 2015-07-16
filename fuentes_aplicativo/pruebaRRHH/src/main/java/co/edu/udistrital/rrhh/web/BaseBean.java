package co.edu.udistrital.rrhh.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseBean {
	
	public void redirect(String url){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		System.out.println(ec.getContextName());
		try {
			ec.redirect("/"+ec.getContextName()+url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void forward (String url, HashMap<String, Object> atributos){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		HttpServletResponse response = (HttpServletResponse) ec.getResponse();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/public/index.jsf");
		for (Entry<String, Object> elemento : atributos.entrySet()) {
			request.setAttribute(elemento.getKey(), elemento.getValue());
		}
		try {
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}

}
