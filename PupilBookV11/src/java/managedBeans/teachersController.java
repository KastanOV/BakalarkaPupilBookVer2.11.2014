/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Sheduleitem;
import Entity.Student;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Teacher;
import Entity.Users;
import SessionBeans.TeachersSessionBeanLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class teachersController implements Serializable {
    @EJB
    private TeachersSessionBeanLocal sb;
    
    private Studygroup editedStudyGroup;
    private Studysubject editedStudySubject;
    private Student editedStudent;
    private Users loggedUser;
    private Collection<Sheduleitem> selectedSheduleItems;
    private List<Student> selectedStudents;

    //TODO mozna budou problemy s odhlasenim, nebot by mohl zustat ulozeny Teacher v managedBeane, nutno otestovat
    /**
     * Creates a new instance of teachersController
     */
    public teachersController() {
        
    }
    public List<Studygroup> getStudyGroups(){
        
        return sb.getStudyGroups(loggedUser.getLogin(), loggedUser.getPassword());
    }
    public List<Studysubject> getStudySubject(){
        if(editedStudyGroup != null){
            List<Studysubject> tmp = sb.getStudySubjects(editedStudyGroup, loggedUser.getLogin());
            return tmp;
        } else {
            return null;
        }
    }
    public List<Student> getStudents(){
        if(editedStudyGroup != null){
            selectedStudents = sb.getStudents(loggedUser.getLogin(), loggedUser.getPassword(), editedStudyGroup.getIdStudyGroup());
            return selectedStudents;
        }else return null;
    }
    public Studygroup getEditedStudyGroup() {
        return editedStudyGroup;
    }
    public Sheduleitem getSheduleitem(short day, short hour){
        if(selectedSheduleItems == null){
            selectedSheduleItems = sb.getSheduleItems(loggedUser.getLogin(), loggedUser.getPassword());
        } 
        for (Sheduleitem item : selectedSheduleItems){
            if(item.getDay() == day && item.getHour() == hour) 
                return item;
        }
        Sheduleitem free = new Sheduleitem();
        free.setStudyGroupidStudyGroup(new Studygroup(null, ""));
        free.setStudySubjectidStudySubject(new Studysubject(null, "Volná hodina", null));
        return free;
    }

    public void setEditedStudyGroup(Studygroup editedStudyGroup) {
        this.editedStudyGroup = editedStudyGroup;
    }

    public Studysubject getEditedStudySubject() {
        return editedStudySubject;
    }

    public void setEditedStudySubject(Studysubject editedStudySubject) {
        this.editedStudySubject = editedStudySubject;
    }

    public Student getEditedStudent() {
        return editedStudent;
    }

    public void setEditedStudent(Student editedStudent) {
        this.editedStudent = editedStudent;
    }
    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Teacher loggedUser) {
        this.loggedUser = loggedUser;
    }
    
}
