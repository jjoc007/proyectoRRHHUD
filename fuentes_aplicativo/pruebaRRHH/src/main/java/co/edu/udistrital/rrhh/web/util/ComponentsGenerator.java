package co.edu.udistrital.rrhh.web.util;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.domain.Rol;
import co.edu.udistrital.rrhh.web.converter.CargoConverter;
import co.edu.udistrital.rrhh.web.converter.RolConverter;

public class ComponentsGenerator {

	public static final int BIGDECIMAL = 1;
	public static final int STRING = 2;
	public static final int INTEGER = 3;

	public static AutoComplete getAutocompleteRol(String idComponent, String valueExpression){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		AutoComplete autoCompleteLineaGenerico = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
		autoCompleteLineaGenerico.setId(idComponent);
		autoCompleteLineaGenerico.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, Rol.class));
		autoCompleteLineaGenerico.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{autoCompleteBean.completeRol}", List.class, new Class[] { String.class }));
		autoCompleteLineaGenerico.setDropdown(true);
		autoCompleteLineaGenerico.setValueExpression("var", expressionFactory.createValueExpression(elContext, "rol", String.class));
		autoCompleteLineaGenerico.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{rol.rolNombre}", String.class));
		autoCompleteLineaGenerico.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{rol}", Rol.class));
		autoCompleteLineaGenerico.setScrollHeight(300);
		autoCompleteLineaGenerico.setConverter(new RolConverter());
		autoCompleteLineaGenerico.setRequired(false);

		return autoCompleteLineaGenerico;
	}
	
	public static AutoComplete getAutocompleteCargo(String idComponent, String valueExpression){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		AutoComplete autoCompleteLineaGenerico = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
		autoCompleteLineaGenerico.setId(idComponent);
		autoCompleteLineaGenerico.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, Cargo.class));
		autoCompleteLineaGenerico.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{autoCompleteBean.completeCargo}", List.class, new Class[] { String.class }));
		autoCompleteLineaGenerico.setDropdown(true);
		autoCompleteLineaGenerico.setValueExpression("var", expressionFactory.createValueExpression(elContext, "car", String.class));
		autoCompleteLineaGenerico.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{car.carNombre}", String.class));
		autoCompleteLineaGenerico.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{car}", Cargo.class));
		autoCompleteLineaGenerico.setScrollHeight(300);
		autoCompleteLineaGenerico.setConverter(new CargoConverter());
		autoCompleteLineaGenerico.setRequired(false);

		return autoCompleteLineaGenerico;
	}
	

	//menus
	public static SelectOneMenu getAutocompleteEstadoActual(String idComponent, String valueExpression){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		SelectOneMenu menuEstadoGenerico =(SelectOneMenu) application.createComponent(SelectOneMenu.COMPONENT_TYPE);
		menuEstadoGenerico.setId(idComponent);
		menuEstadoGenerico.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, String.class));

		UISelectItem itemActivo = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		itemActivo.setItemLabel("Activo");
		itemActivo.setItemValue(Constantes.GENERAL_ESTADO_ACTIVO);

		UISelectItem itemInactivo = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		itemInactivo.setItemLabel("Inactivo");
		itemInactivo.setItemValue(Constantes.GENERAL_ESTADO_INACTIVO);

		menuEstadoGenerico.getChildren().add(itemActivo);
		menuEstadoGenerico.getChildren().add(itemInactivo);


		return menuEstadoGenerico;

	}


	public static SelectOneMenu getAutocompleteTipoConceptosPer(String idComponent, String valueExpression){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		SelectOneMenu menuGenerico =(SelectOneMenu) application.createComponent(SelectOneMenu.COMPONENT_TYPE);
		menuGenerico.setId(idComponent);
		menuGenerico.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, BigDecimal.class));

		UISelectItem item1 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item1.setItemLabel("Devengo");
		item1.setItemValue(Constantes.TIPO_CONCEPTO_DEVENGO);

		UISelectItem item2 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item2.setItemLabel("Deducido");
		item2.setItemValue(Constantes.TIPO_CONCEPTO_DEDUCIDO);
		
		UISelectItem item3 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item3.setItemLabel("Otros");
		item3.setItemValue(Constantes.TIPO_CONCEPTO_OTROS);

		menuGenerico.getChildren().add(item1);
		menuGenerico.getChildren().add(item2);
		menuGenerico.getChildren().add(item3);


		return menuGenerico;

	}
	
	public static SelectOneMenu getAutocompleteTipoConceptos(String idComponent, String valueExpression){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		SelectOneMenu menuGenerico =(SelectOneMenu) application.createComponent(SelectOneMenu.COMPONENT_TYPE);
		menuGenerico.setId(idComponent);
		menuGenerico.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, BigDecimal.class));

		UISelectItem item1 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item1.setItemLabel("Porcentaje");
		item1.setItemValue(Constantes.TIPO_CONCEPTO_PORCENTAJE);

		UISelectItem item2 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item2.setItemLabel("Valor");
		item2.setItemValue(Constantes.TIPO_CONCEPTO_VALOR);
		
		menuGenerico.getChildren().add(item1);
		menuGenerico.getChildren().add(item2);


		return menuGenerico;

	}
	
	public static SelectOneMenu getAutocompleteTipoEntidad(String idComponent, String valueExpression){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		SelectOneMenu menuGenerico =(SelectOneMenu) application.createComponent(SelectOneMenu.COMPONENT_TYPE);
		menuGenerico.setId(idComponent);
		menuGenerico.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, String.class));

		UISelectItem item1 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item1.setItemLabel("Salud");
		item1.setItemValue(Constantes.TIPO_ENTIDAD_SALUD);

		UISelectItem item2 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item2.setItemLabel("Pension");
		item2.setItemValue(Constantes.TIPO_ENTIDAD_PENSION);
		
		UISelectItem item3 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item3.setItemLabel("Cesantias");
		item3.setItemValue(Constantes.TIPO_ENTIDAD_CESANTIAS);

		UISelectItem item4 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item4.setItemLabel("ARP");
		item4.setItemValue(Constantes.TIPO_ENTIDAD_ARP);

		UISelectItem item5 = (UISelectItem) application.createComponent(UISelectItem.COMPONENT_TYPE);
		item5.setItemLabel("Caja de compensacion");
		item5.setItemValue(Constantes.TIPO_ENTIDAD_CAJA_COMPENSACION);

		
		menuGenerico.getChildren().add(item1);
		menuGenerico.getChildren().add(item2);
		menuGenerico.getChildren().add(item3);
		menuGenerico.getChildren().add(item4);
		menuGenerico.getChildren().add(item5);


		return menuGenerico;

	}

	//checkbox
	public static SelectBooleanCheckbox getBasicCheckBox(String idComponent, String valueExpression){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		SelectBooleanCheckbox check =(SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
		check.setId(idComponent);
		check.setImmediate(true);

		check.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, Boolean.class));

		return check;

	}

	//OutputLabel
	public static OutputLabel getBasicOutputLabel(String idFor, String id, String value){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();

		OutputLabel Output = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
		Output.setFor(idFor);
		Output.setId(id);
		Output.setValue(value);

		return Output;
	}

	//InputText
	public static InputText getBasicInputText(String idComponent, String valueExpression, int typeContent){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		InputText inputText = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
		inputText.setId(idComponent);

		if(typeContent == ComponentsGenerator.STRING)
			inputText.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, String.class));
		else if(typeContent == ComponentsGenerator.BIGDECIMAL)
			inputText.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, BigDecimal.class));
		else if(typeContent == ComponentsGenerator.INTEGER)
			inputText.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, Integer.class));

		inputText.setRequired(true);

		return inputText;
	}

	//Message
	public static Message getBasicMessage(String idFor, String id){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();

		Message message = (Message) application.createComponent(Message.COMPONENT_TYPE);
		message.setId(id);
		message.setFor(idFor);
		message.setDisplay("icon");

		return message;
	}

	//calendar
	public static Calendar getBasicCalendar(String idComponent, String valueExpression){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		Calendar calendar = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
		calendar.setId(idComponent);
		calendar.setValueExpression("value", expressionFactory.createValueExpression(elContext, valueExpression, Date.class));
		calendar.setNavigator(true);
		calendar.setEffect("slideDown");
		calendar.setPattern("dd/MM/yyyy");
		calendar.setRequired(true);

		return calendar;
	}

}