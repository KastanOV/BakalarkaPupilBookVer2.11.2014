/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Users;
import SessionBeans.AdminmainSessionBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;


/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class AdminmainController implements Serializable{
    @EJB
    private AdminmainSessionBeanLocal sb;
    
    private Schoolyear editedSchoolYear;
    private Studygroup editedStudygroup;
    private Users editedUser;
    private List<Users> dropedStudents = new ArrayList<>();
    private Studysubject[][] SheduleItems;

    public void onStudentDrop(DragDropEvent ddEvent){
        Users s = ((Users) ddEvent.getData());
        try {
            editedStudygroup.getUsersCollection().add(s);
            s.setStudyGroupidStudyGroup(editedStudygroup);
            saveStudent(s);
        } catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Zřejmě jste nevybral studíjní skupinu", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
        }
    }
    public Collection<Users> getStudents() {
        return sb.getAllStudents();
    }
    public Collection<Users> getTeachers(){
        return sb.getAllTeachers();
    }
    
    public void saveStudent(Users u){
        try{
            sb.saveUser(u);
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    public void saveStudent(){
        try{
            sb.createNewUser(editedUser);
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    public void saveSchoolYears(){
		try {
			sb.saveSchoolyear(editedSchoolYear);
                        
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
	}
    public void saveStudyGroup(){
        try{
            //editedSchoolYear.getStudygroupCollection().add(editedStudygroup);
            editedStudygroup.setSchoolYearidSchoolYear(editedSchoolYear);
            sb.saveStudygroup(editedStudygroup);
        }   catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
	}
    }
    
    public Users prepareNewStudent(){
        editedUser = new Users();
        editedUser.setRole('S');
        return editedUser;
    }
    public Users prepareNewTeacher(){
        editedUser = new Users();
        editedUser.setRole('T');
        return editedUser;
    }
    public Schoolyear prepareNewSchoolYear(){
        editedSchoolYear = new Schoolyear();
        return editedSchoolYear;
    }
    public Studygroup prepareNewStudyGroup(){
        editedStudygroup = new Studygroup();
//        for (int i = 0; i < 10; i++){
//            for (int j = 0; j < 5; j++){
//                
//            }
//        }
        return editedStudygroup;
    }
    
    public List<Schoolyear> getAllSchoolYears(){
        return sb.getAllSchoolYears();
    }
    public List<Studygroup> getEditedStudyGroups(){
        return sb.getEditedStudyGroup(editedSchoolYear);
    }
    public Studygroup getEditedStudygroup() {
        return editedStudygroup;
    }
    
    public void setEditedStudygroup(Studygroup editedStudygroup) {
        this.editedStudygroup = editedStudygroup;
    }
    public Schoolyear getEdited() {
        return editedSchoolYear;
    }
    public void setEdited(Schoolyear edited) {
        this.editedSchoolYear = edited;
        
    }
    public Users getEditedUser() {
        return editedUser;
    }
    public void setEditedUser(Users editedUser) {
        this.editedUser = editedUser;
    }
    public Collection<Users> getDropedStudents() {
        if(editedStudygroup != null){
            return editedStudygroup.getUsersCollection();
        } else {
            return null;
        }
        
    }
    
}
