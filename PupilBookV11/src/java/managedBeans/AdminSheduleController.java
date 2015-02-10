/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Sheduleitem;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Teacher;
import SessionBeans.SheduleItemsSBLocal;
import SessionBeans.StudySubjectsSBLocal;
import SessionBeans.TeachersSBLocal;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



/**
 *
 * @author Topr
 */
@ManagedBean
@SessionScoped
public class AdminSheduleController implements Serializable{

    
    @EJB
    private SheduleItemsSBLocal sheduleItemsSB;
    @EJB
    private StudySubjectsSBLocal studySubjectsSB;
    @EJB
    private TeachersSBLocal teachersSB;
    
    private AdminMainController adminMain;
    private Teacher editedTeacher;
    private Collection<Sheduleitem> selectedSheduleItems;
    private Sheduleitem editedSheduleItem;
    private Studysubject editedStudySubject;
    private Studygroup editedStudyGroup;

    public void setMainControler(AdminMainController AdminMain) {
        this.adminMain = AdminMain;
        editedStudyGroup = adminMain.getEditedStudygroup();
        if(editedStudyGroup != null){
            selectedSheduleItems = sheduleItemsSB.getSheduleItems(editedStudyGroup);
        } else {
            selectedSheduleItems = null;
        }
    }
        
    
    public AdminSheduleController() {
    }
    
    public Collection<Teacher> getSheduleTeachers(){
        Collection<Teacher> u = teachersSB.getTeachersForShedule(editedSheduleItem.getDay(), editedSheduleItem.getHour());
        return u;
    }
    
    public Sheduleitem getSheduleitem(short day, short hour){
        if(selectedSheduleItems != null){
            for (Sheduleitem item : selectedSheduleItems){
                if(item.getDay() == day && item.getHour() == hour) return item;
            }
        }
        return null;
    }
    public void setEditedSheduleItem(short day, short hour){
        
        if(selectedSheduleItems != null){
            for (Sheduleitem item : selectedSheduleItems){
                if(item.getDay() == day && item.getHour() == hour){
                    editedSheduleItem = item;
                    editedTeacher = (Teacher) item.getUsersLogin();
                    setEditedStudySubject(item.getStudySubjectidStudySubject());
                    break;
                }
            }
        }
    }
    public Sheduleitem getEditedSheduleItem() {
        return editedSheduleItem;
    }

    public void setEditedSheduleItem(Sheduleitem editedSheduleItem) {
        this.editedSheduleItem = editedSheduleItem;
    }
    
    public void saveEditedSheduleItem(Teacher u, Studysubject s){
        editedSheduleItem.setUsersLogin(u);
        editedSheduleItem.setStudySubjectidStudySubject(s);
        try{
            sheduleItemsSB.saveSheduleItem(editedSheduleItem);
        
        }catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    
    public Studysubject getEditedStudySubject() {
        return editedStudySubject;
    }

    public void setEditedStudySubject(Studysubject editedStudySubject) {
        this.editedStudySubject = editedStudySubject;
    }
    public Teacher getEditedTeacher() {
        return editedTeacher;
    }

    public void setEditedTeacher(Teacher editedTeacher) {
        this.editedTeacher = editedTeacher;
    }
    public Collection<Studysubject> getStudySubjects(){
        Collection<Studysubject> s = studySubjectsSB.getAllStudySubjects();
        return s;
    }
}
