/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Teacher;
import SessionBeans.TeachersSBLocal;
import SessionBeans.UsersSBLocal;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author Topr
 */
@ManagedBean
@SessionScoped
public class AdminTeacherController implements Serializable{

    @EJB
    private TeachersSBLocal teachersSB;
    @EJB
    private UsersSBLocal UsersSB;
        
    private AdminMainController adminMain;
    public void setMainControler(AdminMainController AdminMain) {
        this.adminMain = AdminMain;
    }

    private Teacher editedTeacher;
    
    public AdminTeacherController() {
    }
    
    public Teacher prepareNewTeacher(){
        editedTeacher = new Teacher();
        return editedTeacher;
    }
    
    public void onTeacherDrop(DragDropEvent ddEvent){
        Teacher s = ((Teacher) ddEvent.getData());
        try {
            adminMain.getEditedStudygroup().getUsersCollection().add(s);
            s.setStudyGroupidStudyGroup(adminMain.getEditedStudygroup());
            saveTeacher(s);
        } catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Zřejmě jste nevybral studíjní skupinu", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
        }
    }
    
    public void saveTeacher(Teacher u){
        try{
            if(u.getLogin() != null){
                teachersSB.saveTeacher(u);    
            }else teachersSB.createNewTeacher(editedTeacher);
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    public void saveTeacher(){
        try{
            if(editedTeacher.getLogin() == null){
                teachersSB.createNewTeacher(editedTeacher);
            }
            else {
                teachersSB.saveTeacher(editedTeacher);
            }
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    public void deleteTeacher(){
        UsersSB.deleteUser(editedTeacher);
    }
    public Collection<Teacher> getDropedTeachers() {
        if(adminMain.getEditedStudygroup() != null){
            return teachersSB.getTeachersByStudyGroup(adminMain.getEditedStudygroup());
        } else {
            return null;
        }
    }
    public Collection<Teacher> getTeachers(){
        Collection<Teacher> u = teachersSB.getAllTeachers() ;
        return u;
    }
    public Teacher getEditedTeacher() {
        return editedTeacher;
    }

    public void setEditedTeacher(Teacher editedTeacher) {
        this.editedTeacher = editedTeacher;
    }
}
