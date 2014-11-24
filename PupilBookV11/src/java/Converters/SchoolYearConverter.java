/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entity.Schoolyear;
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
 * @author KastanNotas
 */
@ManagedBean (name = "SchoolYearConverter")
@SessionScoped
public class SchoolYearConverter implements Converter{

    @EJB
    private AdminmainSessionBeanLocal sb;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
                int id = 0;
		if(value == null){
                    return null;
                }
                if(value instanceof String){
                    try{
                        id = Integer.parseInt(value);
                        } catch (Exception e){
                            return null;
                        }
                }
		try{
			id = Integer.parseInt(value);
		} catch(NumberFormatException ex){
			context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nelze převést string na číslo ID.", "Chyba konvertoru kontaktujete Administrátora systému." + ex.getMessage()));
			throw new ConverterException("Nelze provést konverzi!", ex);
		}
		return sb.getSchoolyear(id);
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value == null){
			return "null";
		}
		if(value instanceof Schoolyear){
			return Integer.toString(((Schoolyear)value).getIdSchoolYear());
		}
                if(value instanceof String){
                    if("".equals(value)) return null;
                }
		context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entitu nelze převést.", "Chyba konvertoru kontaktujete Administrátora systému."));
		throw new ConverterException("Nelze provést konverzi!");
	}
    
}
