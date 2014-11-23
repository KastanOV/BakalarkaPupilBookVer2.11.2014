/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServicesEntities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Topr
 */
@XmlRootElement
public class ServiceTransactionObject {
    private Integer SchoolYearID;
    private String SchoolYearName;
    
    private List<AttendanceService> Attendances = new ArrayList<>();
    private List<InformationService> Informations = new ArrayList<>();
    private List<ResultService> Results = new ArrayList<>();
    private List<SheduleItemService> SheduleItems = new ArrayList<>();
    private List<StudentService> Students = new ArrayList<>();
    private List<StudyGroupService> StudyGroups = new ArrayList<>();
    private List<StudySubjectService> StudySubjects = new ArrayList<>();

    public Integer getSchoolYearID() {
        return SchoolYearID;
    }

    public void setSchoolYearID(Integer SchoolYearID) {
        this.SchoolYearID = SchoolYearID;
    }

    public String getSchoolYearName() {
        return SchoolYearName;
    }

    public void setSchoolYearName(String SchoolYearName) {
        this.SchoolYearName = SchoolYearName;
    }

    public List<AttendanceService> getAttendances() {
        return Attendances;
    }

    public void setAttendances(List<AttendanceService> Attendances) {
        this.Attendances = Attendances;
    }

    public List<InformationService> getInformations() {
        return Informations;
    }

    public void setInformations(List<InformationService> Informations) {
        this.Informations = Informations;
    }

    public List<ResultService> getResults() {
        return Results;
    }

    public void setResults(List<ResultService> Results) {
        this.Results = Results;
    }

    public List<SheduleItemService> getSheduleItems() {
        return SheduleItems;
    }

    public void setSheduleItems(List<SheduleItemService> SheduleItems) {
        this.SheduleItems = SheduleItems;
    }

    public List<StudentService> getStudents() {
        return Students;
    }

    public void setStudents(List<StudentService> Students) {
        this.Students = Students;
    }

    public List<StudyGroupService> getStudyGroups() {
        return StudyGroups;
    }

    public void setStudyGroups(List<StudyGroupService> StudyGroups) {
        this.StudyGroups = StudyGroups;
    }

    public List<StudySubjectService> getStudySubjects() {
        return StudySubjects;
    }

    public void setStudySubjects(List<StudySubjectService> StudySubjects) {
        this.StudySubjects = StudySubjects;
    }
}
