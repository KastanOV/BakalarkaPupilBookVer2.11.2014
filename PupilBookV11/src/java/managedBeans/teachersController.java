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
import SessionBeans.TeachersSessionBeanLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

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
    private LineChartModel classificationModel;
    private Results editedClassification;
    private Integer editedScore = 5;

    public Integer getEditedScore() {
        return editedScore;
    }

    public void setEditedScore(Integer editedScore) {
        this.editedScore = editedScore;
    }
    public void onrate(RateEvent rateEvent) {
        this.editedScore = (((Integer) rateEvent.getRating()).intValue());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * Creates a new instance of teachersController
     */
    public void saveClassification(){
        
    }
    private void createClassificationModel(){
        classificationModel = initClassificationModel();
        classificationModel.setTitle("Klasifikace");
        classificationModel.setLegendPosition("e");
        classificationModel.setShowPointLabels(true);
        classificationModel.getAxes().put(AxisType.X, new CategoryAxis("Student " + editedStudent.getLastName() + " " + editedStudent.getFirstName()));
        Axis yAxis = classificationModel.getAxis(AxisType.Y);
        yAxis.setLabel("Známky");
        
        yAxis.setMin(0);
        yAxis.setMax(5);
    }
    private LineChartModel initClassificationModel(){
        LineChartModel model = new LineChartModel();
        ChartSeries classification = new ChartSeries("Známky");
            if(editedStudent != null && editedStudySubject != null){
                for(Results item : sb.getStudentResults(editedStudent.getLogin(), editedStudySubject.getIdStudySubject())){
                    double score = (double) (item.getScore() + 1)  / 2;
                classification.set(item.getDescription(), score);
                
            }
            model.addSeries(classification);
        }
        return model;
    } 
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
    public void prepareNewClassification(){
        editedClassification = new Results();
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
    public LineChartModel getClassificationModel() {
        createClassificationModel();
        return classificationModel;
    }
    public Results getEditedClassification() {
        return editedClassification;
    }

    public void setEditedClassification(Results editedClassification) {
        this.editedClassification = editedClassification;
    }
    
        
}
