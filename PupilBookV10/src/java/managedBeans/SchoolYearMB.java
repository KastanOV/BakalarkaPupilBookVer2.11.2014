/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Schoolyear;
import SessionBeans.SchoolYearSBLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class SchoolYearMB implements Serializable{
    @EJB
    private SchoolYearSBLocal db;
    
    private Schoolyear edited;
  
    private List<SelectItem> it ;
    public Schoolyear getEdited() {
        return edited;
    }

    public void setEdited(Schoolyear edited) {
        if(edited != null)
        this.edited = edited;
    }
    
    public List<SelectItem> getAllSchoolYears(){
        it = new ArrayList<>();
        for(Schoolyear item : db.getAllSchoolYears()){
        it.add(new SelectItem(item, item.getName()));
        }
        return it;
    }
    public Schoolyear prepareNew(){
        edited = new Schoolyear();
        return edited;
    }
    public String saveSchoolYears(){
		try {
			db.saveSchoolyear(edited);
                        
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
			e.printStackTrace();
			return "";
		}
		return "index";
	}
    
    
}
