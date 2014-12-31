/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Sheduleitem;
import Entity.Student;
import SessionBeans.SchoolYearSBLocal;
import SessionBeans.SheduleItemsSBLocal;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class AdminMainController implements Serializable{
    
    @EJB
    private StudentsSBLocal studentsSB;
    @EJB
    private SchoolYearSBLocal schoolYearSB;
    @EJB
    private StudyGroupsSBLocal studyGroupSB;
    @EJB
    private SheduleItemsSBLocal sheduleItemsSB;
    @EJB
    private StudySubjectsSBLocal studySubjectsSB;
    
    private Schoolyear editedSchoolYear;
    private Studygroup editedStudygroup;
    private Collection<Sheduleitem> selectedSheduleItems;
    private Studysubject editedStudySubject;
    private Collection<Studysubject> StudySubjects;

    
    private String searchByLastname;

    public String getSearchByLastname() {
        return searchByLastname;
    }

    public void setSearchByLastname(String SearchByLastname) {
        this.searchByLastname = SearchByLastname;
    }
    
    public Collection<Student> getStudents() {
        if(searchByLastname == null || searchByLastname.equals("")){
            return studentsSB.getAllStudents();
        } else {
            return studentsSB.getByLastName(searchByLastname);
        }
        
    }

    public void saveSchoolYears(){
		try {
			schoolYearSB.saveSchoolyear(editedSchoolYear);
                        
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
            
            studyGroupSB.saveStudygroup(editedStudygroup);
            for (short i = 0; i < 8; i++){
                for (short j = 0; j < 5; j++){
                    Sheduleitem item = new Sheduleitem();
                    item.setStudyGroupidStudyGroup(editedStudygroup);
                    item.setDay(j);
                    item.setHour(i);
                    sheduleItemsSB.insertNewSheduleItem(item);
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
            studySubjectsSB.insertNewStudySubject(editedStudySubject);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
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
        return schoolYearSB.getAllSchoolYears();
    }
    public List<Studygroup> getEditedStudyGroups(){
        return studyGroupSB.getEditedStudyGroup(editedSchoolYear);
    }
    public Studygroup getEditedStudygroup() {
        if(editedStudygroup != null) {
            selectedSheduleItems = editedStudygroup.getSheduleitemCollection();
            return editedStudygroup;
        }
        else{
            return null;
        }
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
    public void setEditedStudySubject(Studysubject editedStudySubject) {
        this.editedStudySubject = editedStudySubject;
    }
    
    public Collection<Studysubject> getStudySubjects() {
        return studySubjectsSB.getAllStudySubjects();
    }

    public void setStudySubjects(Collection<Studysubject> StudySubjects) {
        this.StudySubjects = StudySubjects;
    }

    public Studysubject getEditedStudySubject() {
        return editedStudySubject;
    }
}
