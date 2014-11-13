/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import Entity.Users;
import SessionBeans.SchoolYearSBLocal;
import SessionBeans.StudentsSBLocal;
import SessionBeans.StudyGroupSBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class AdminmainController{
    @EJB
    private SchoolYearSBLocal dbSchoolYear;
    @EJB
    private StudyGroupSBLocal dbStudyGroup;
    @EJB
    private StudentsSBLocal dbStudents;
    
    private Schoolyear edited;
    private Studygroup editedStudygroup;
    private Users editedUser;

    public Users getEditedUser() {
        return editedUser;
    }

    public void setEditedUser(Users editedUser) {
        this.editedUser = editedUser;
    }
    public Users prepareNewUser(){
        editedUser = new Users();
        return editedUser;
    }
    
    public List<Schoolyear> getAllSchoolYears(){
        return dbSchoolYear.getAllSchoolYears();
    }
    public Schoolyear prepareNew(){
        edited = new Schoolyear();
        return edited;
    }
    public Studygroup prepareNewStudyGroup(){
        editedStudygroup = new Studygroup();
        return editedStudygroup;
    }
    public void saveSchoolYears(){
		try {
			dbSchoolYear.saveSchoolyear(edited);
                        
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
	}
    public void saveStudyGroup(){
        try{
            edited.getStudygroupCollection().add(editedStudygroup);
            editedStudygroup.setSchoolYearidSchoolYear(edited);
            dbStudyGroup.saveStudygroup(editedStudygroup);
            int bla = 0;
        }   catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
	}
        
    }
    
    public Studygroup getEditedStudygroup() {
        return editedStudygroup;
    }

    public void setEditedStudygroup(Studygroup editedStudygroup) {
        this.editedStudygroup = editedStudygroup;
    }
    
    public Schoolyear getEdited() {
        return edited;
    }

    public void setEdited(Schoolyear edited) {
        this.edited = edited;
        
    }
    
    
}
