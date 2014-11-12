/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import SessionBeans.StudyGroupSBLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class StudyGroupMB implements Serializable{
    @EJB
    private StudyGroupSBLocal db;
    
    private Studygroup edited;
    private Schoolyear sy;
    private List<SelectItem> it;
    
    public Studygroup getEdited() {
        return edited;
    }
    public Studygroup prepareNew(Schoolyear sy){
        edited = new Studygroup();
        edited.setSchoolYearidSchoolYear(sy);
        return edited;
    }
    public void setEdited(Studygroup edited) {
        if(edited != null)
        this.edited = edited;
    }

    public String saveStudyGroups(){
		try {
		db.saveStudygroup(edited);
                        
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
