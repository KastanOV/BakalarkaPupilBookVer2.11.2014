/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Schoolyear;
import SessionBeans.SchoolyearFacadeLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
@FacesConverter("SchoolYearConverter")
public class ConverterSchoolYear implements Converter{
    
    @EJB
    private SchoolyearFacadeLocal SchoolYear;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
        if("null".equals(value)){
            return null;
	}
	int id = 0;
        try{
            id = Integer.parseInt(value);
            
	} catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
	}
	return SchoolYear.getSchoolYear(id);
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if(value == null){
			return "null";
		}
		if(value instanceof Schoolyear){
			return Integer.toString(((Schoolyear)value).getIdSchoolYear());
		}
	else {
            return null;
        }
    }   
}
