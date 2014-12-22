/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Student;
import SessionBeans.AdminmainSessionBeanLocal;
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
public class AdminStudentController {
    private AdminmainController adminMain;
    public void setMainControler(AdminmainController AdminMain) {
        this.adminMain = AdminMain;
    }
    @EJB
    private AdminmainSessionBeanLocal sb;
    private Student editedStudent;
    
    public AdminStudentController() {
        
    }
    
    public Student prepareNewStudent(){
        editedStudent = new Student();
        return editedStudent;
    }
    
    public void saveStudent(Student u){
        try{
            if(u.getLogin() != null){
                sb.saveUser(u);    
            }else sb.createNewUser(editedStudent);
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    
    public void onStudentDrop(DragDropEvent ddEvent){
        Student s = ((Student) ddEvent.getData());
        try {
            adminMain.getEditedStudygroup().getUsersCollection().add(s);
            s.setStudyGroupidStudyGroup(adminMain.getEditedStudygroup());
            saveStudent(s);
        } catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Zřejmě jste nevybral studíjní skupinu", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
        }
    }
    
    public void saveStudent(){
        try{
            if(editedStudent.getLogin() == null){
                sb.createNewUser(editedStudent);
            }
            else {
                sb.saveUser(editedStudent);
            }
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    
    public Collection<Student> getDropedStudents() {
        if(adminMain.getEditedStudygroup() != null){
            return sb.getStudentByStudyGroup(adminMain.getEditedStudygroup());
        } else {
            return null;
        }
    }
    
    public Collection<Student> getStudents() {
        //if(searchByLastname == null || searchByLastname.equals("")){
            return sb.getAllStudents();
        //} else {
            //return sb.getByLastName(searchByLastname);
        //}
        
    }

    public Student getEditedStudent() {
        return editedStudent;
    }

    public void setEditedStudent(Student editedStudent) {
        this.editedStudent = editedStudent;
    }
}
