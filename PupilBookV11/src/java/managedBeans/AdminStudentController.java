/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Student;
import SessionBeans.StudentsSBLocal;
import SessionBeans.UsersSBLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
public class AdminStudentController implements Serializable{
    
    @EJB
    private StudentsSBLocal studentsSB;
    @EJB
    private UsersSBLocal UsersSB;
        
    private AdminMainController adminMain;
    public void setMainControler(AdminMainController AdminMain) {
        this.adminMain = AdminMain;
    }
    
    private Student editedStudent;
    private String searchByLastname;
    private Date birthDateStart;
    private Date birthDateEnd;
    private boolean showDeletedUsers;

    public AdminStudentController() {
        showDeletedUsers = false;
    }
    
    public Student prepareNewStudent(){
        editedStudent = new Student();
        return editedStudent;
    }
    
    public void saveStudent(Student u){
        try{
            if(u.getLogin() != null){
                studentsSB.saveUser(u);    
            }else studentsSB.createNewUser(editedStudent);
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    public void deleteStudent(){
        UsersSB.deleteUser(editedStudent);
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
                studentsSB.createNewUser(editedStudent);
            }
            else {
                studentsSB.saveUser(editedStudent);
            }
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    
    public Collection<Student> getDropedStudents() {
        if(adminMain.getEditedStudygroup() != null){
            return studentsSB.getStudentByStudyGroup(adminMain.getEditedStudygroup());
        } else {
            return null;
        }
    }
    
    public Collection<Student> getStudents() {
        if(searchByLastname == null){
            searchByLastname = "";
        }
        return studentsSB.getByParameters(searchByLastname, birthDateStart, birthDateEnd, showDeletedUsers);
    }

    public Student getEditedStudent() {
        return editedStudent;
    }

    public void setEditedStudent(Student editedStudent) {
        this.editedStudent = editedStudent;
    }
    
    public String getSearchByLastname() {
        return searchByLastname;
    }

    public void setSearchByLastname(String SearchByLastname) {
        this.searchByLastname = SearchByLastname;
    }
    public Date getBirthDateStart() {
        return birthDateStart;
    }

    public void setBirthDateStart(Date birthDateStart) {
        this.birthDateStart = birthDateStart;
    }

    public Date getBirthDateEnd() {
        return birthDateEnd;
    }

    public void setBirthDateEnd(Date birthDateEnd) {
        this.birthDateEnd = birthDateEnd;
    }
    
    public boolean isShowDeletedUsers() {
        return showDeletedUsers;
    }

    public void setShowDeletedUsers(boolean showDeletedUsers) {
        this.showDeletedUsers = showDeletedUsers;
    }
}
