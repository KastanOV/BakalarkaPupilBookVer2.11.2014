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
import Entity.Sheduleitem;
import Entity.Student;
import Entity.Teacher;
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
    private Student editedStudent;

    private Teacher editedTeacher;
    private List<Users> dropedStudents = new ArrayList<>();
    private Collection<Sheduleitem> selectedSheduleItems;
    private Studysubject editedStudySubject;
    private Sheduleitem editedSheduleItem;
    private String searchByLastname;

    public String getSearchByLastname() {
        return searchByLastname;
    }

    public void setSearchByLastname(String SearchByLastname) {
        this.searchByLastname = SearchByLastname;
    }
    
    public Sheduleitem getEditedSheduleItem() {
        return editedSheduleItem;
    }

    public void setEditedSheduleItem(Sheduleitem editedSheduleItem) {
        this.editedSheduleItem = editedSheduleItem;
    }

    public void onStudentDrop(DragDropEvent ddEvent){
        Student s = ((Student) ddEvent.getData());
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
    public void onTeacherDrop(DragDropEvent ddEvent){
        Teacher s = ((Teacher) ddEvent.getData());
        try {
            editedStudygroup.getUsersCollection().add(s);
            s.setStudyGroupidStudyGroup(editedStudygroup);
            saveTeacher(s);
        } catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Zřejmě jste nevybral studíjní skupinu", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
        }
    }
    public Collection<Student> getStudents() {
        if(searchByLastname == null || searchByLastname.equals("")){
            return sb.getAllStudents();
        } else {
            return sb.getByLastName(searchByLastname);
        }
        
    }
    public Collection<Teacher> getTeachers(){
        Collection<Teacher> u = sb.getAllTeachers() ;
        return u;
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
    public void saveTeacher(Teacher u){
        try{
            if(u.getLogin() != null){
                sb.saveTeacher(u);    
            }else sb.createNewTeacher(editedTeacher);
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    public void saveTeacher(){
        try{
            if(editedTeacher.getLogin() == null){
                sb.createNewTeacher(editedTeacher);
            }
            else {
                sb.saveTeacher(editedTeacher);
            }
            
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
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
            for (short i = 0; i < 8; i++){
                for (short j = 0; j < 5; j++){
                    Sheduleitem item = new Sheduleitem();
                    item.setStudyGroupidStudyGroup(editedStudygroup);
                    item.setDay(j);
                    item.setHour(i);
                    sb.insertNewSheduleItem(item);
                }
            }
        }   catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
	}
    }
    public void saveStudySubject(){
        try{
            sb.insertNewStudySubject(editedStudySubject);
        }catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    //TODO save study subject
    public void saveEditedSheduleItem(Teacher u, Studysubject s){
        editedSheduleItem.setUsersLogin(u);
        editedSheduleItem.setStudySubjectidStudySubject(s);
        try{
            sb.saveSheduleItem(editedSheduleItem);
        
        }catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
    }
    
    public Student prepareNewStudent(){
        editedStudent = new Student();
        
        return editedStudent;
    }
    public Teacher prepareNewTeacher(){
        editedTeacher = new Teacher();
        return editedTeacher;
    }
    
    public Schoolyear prepareNewSchoolYear(){
        editedSchoolYear = new Schoolyear();
        return editedSchoolYear;
    }
    public Studygroup prepareNewStudyGroup(){
        editedStudygroup = new Studygroup();
        return editedStudygroup;
    }
    public Studysubject prepareNewStudySubject(){
        editedStudySubject = new Studysubject();
        return editedStudySubject;
    }
    
    
    public List<Schoolyear> getAllSchoolYears(){
        return sb.getAllSchoolYears();
    }
    public List<Studygroup> getEditedStudyGroups(){
        return sb.getEditedStudyGroup(editedSchoolYear);
    }
    public Studygroup getEditedStudygroup() {
        if(editedStudygroup != null) selectedSheduleItems = editedStudygroup.getSheduleitemCollection();
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
    
    public Collection<Student> getDropedStudents() {
        if(editedStudygroup != null){
            return sb.getStudentByStudyGroup(editedStudygroup);
        } else {
            return null;
        }
    }
    public Collection<Teacher> getDropedTeachers() {
        if(editedStudygroup != null){
            return sb.getTeachersByStudyGroup(editedStudygroup);
        } else {
            return null;
        }
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
                    editedStudySubject = item.getStudySubjectidStudySubject();
                    break;
                }
            }
        }
    }
    
    public Collection<Studysubject> getStudySubjects(){
        Collection<Studysubject> s = sb.getAllStudySubjects();
        return s;
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

    public Teacher getEditedTeacher() {
        return editedTeacher;
    }

    public void setEditedTeacher(Teacher editedTeacher) {
        this.editedTeacher = editedTeacher;
    }
}
