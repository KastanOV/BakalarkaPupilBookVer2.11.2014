/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Results;
import Entity.Sheduleitem;
import Entity.Student;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Teacher;
import Entity.Users;
import SessionBeans.ResultsSBLocal;
import SessionBeans.StudentsSBLocal;
import SessionBeans.StudyGroupsSBLocal;
import SessionBeans.StudySubjectsSBLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;

/**
 *
 * @author Topr
 */
@ManagedBean
@SessionScoped
public class TeacherMainController implements Serializable{
    @EJB
    private ResultsSBLocal ResultsSB;
    @EJB
    private StudyGroupsSBLocal studyGroupsSB;
    @EJB
    private StudentsSBLocal studentsSB;
    @EJB
    private StudySubjectsSBLocal studySubjectsSB;
    
    private Studygroup editedStudyGroup;
    private Studysubject editedStudySubject;
    private Student editedStudent;
    private Users loggedUser;
    private Collection<Sheduleitem> selectedSheduleItems;
    private List<Student> selectedStudents;
    private String classificationDescription;
    private Integer score;
    private Results editedClassification;
    private List<String> AutoCompleteClassification;
    private boolean showDeletedUsers;

    

    public void setAutoCompleteClassification(List<String> AutoCompleteClassification) {
        this.AutoCompleteClassification = AutoCompleteClassification;
    }

    public List<String> autoComplete(String input) {
        return ResultsSB.getAutoCompleteStrings(editedStudySubject, input, (Teacher) loggedUser);
    }
    
    public TeacherMainController() {
        showDeletedUsers = false;
        if(score == null){
            score = 0;
        }
    }
    
    public void onRate(RateEvent rateEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, editedStudent.getLastName() + " " + editedStudent.getFirstName()
                , editedStudySubject.getName() + ", " + classificationDescription + " " + UtilScores.getText(((Integer) rateEvent.getRating()).intValue() - 1));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    public void saveClassification(){
        editedClassification.setStudentLogin(editedStudent);
        editedClassification.setScore(score.shortValue());
        editedClassification.setTeacherLogin(loggedUser);
        editedClassification.setStudySubjectidStudySubject(editedStudySubject);
        if(classificationDescription != null){
            editedClassification.setDescription(classificationDescription);
        }
        ResultsSB.insertNewResult(editedClassification);
    }
    
    
    public List<Studygroup> getStudyGroups(){
        return studyGroupsSB.getStudyGroups(loggedUser.getLogin(), loggedUser.getPassword());
    }
    
    public List<Studysubject> getStudySubject(){
        if(editedStudyGroup != null){
            List<Studysubject> tmp = studySubjectsSB.getStudySubjects(editedStudyGroup, loggedUser.getLogin());
            return tmp;
        } else {
            return null;
        }
    }
    
    public List<Student> getStudents(){
        if(editedStudyGroup != null){
            selectedStudents = studentsSB.getStudents(loggedUser.getLogin(), loggedUser.getPassword(), editedStudyGroup.getIdStudyGroup());
            return selectedStudents;
        }else return null;
    }
    
    public Studygroup getEditedStudyGroup() {
        return editedStudyGroup;
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
        editedClassification = new Results();
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
    public String getClassificationDescription() {
        return classificationDescription;
    }
    public void setClassificationDescription(String classificationDescription) {
        this.classificationDescription = classificationDescription;
    }
    public Integer getScore() {
       
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public Results getEditedClassification() {
        return editedClassification;
    }

    public void setEditedClassification(Results editedClassification) {
        this.editedClassification = editedClassification;
    }
    public void prepareNewClassification(){
        editedClassification = new Results();
    }
    public void prepareNewClassification(Results desc){
        editedClassification = new Results();
        editedClassification.setDescription(desc.getDescription());
    }
    
    public boolean isShowDeletedUsers() {
        return showDeletedUsers;
    }

    public void setShowDeletedUsers(boolean showDeletedUsers) {
        this.showDeletedUsers = showDeletedUsers;
    }
}
