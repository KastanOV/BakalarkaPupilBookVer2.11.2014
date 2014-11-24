/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entity.Student;
import Entity.Teacher;
import SessionBeans.AdminmainSessionBeanLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Topr
 */
@ManagedBean (name = "ConverterTeacher")
@SessionScoped
public class ConverterTeacher implements Converter{
    @EJB
    private AdminmainSessionBeanLocal sb;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value == null ){
                    return null;
                }
                if(value instanceof String){
                    if(value.equals("")) return null;
                }
		return sb.getTeacher(value);
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value == null){
			return "null";
		}
		
                if(value instanceof Teacher){
			return ((Teacher)value).getLogin();
		}
                
                if(value instanceof String){
                    if("".equals(value)) return null;
                }
		context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entitu nelze převést.", "Chyba konvertoru kontaktujete Administrátora systému."));
		throw new ConverterException("Nelze provést konverzi!");
	}
}
