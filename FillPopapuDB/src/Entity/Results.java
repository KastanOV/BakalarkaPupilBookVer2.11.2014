/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Topr
 */
public class Results implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idResults;

    private String description;

    private short score;

    private Date date;
 
    private Schoolyear schoolYearidSchoolYear;

    private Studysubject studySubjectidStudySubject;

    private Users teacherLogin;

    private Users studentLogin;

    public Results() {
    }

    public Results(Integer idResults) {
        this.idResults = idResults;
    }

    public Results(Integer idResults, short score) {
        this.idResults = idResults;
        this.score = score;
    }

    public Integer getIdResults() {
        return idResults;
    }

    public void setIdResults(Integer idResults) {
        this.idResults = idResults;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Schoolyear getSchoolYearidSchoolYear() {
        return schoolYearidSchoolYear;
    }

    public void setSchoolYearidSchoolYear(Schoolyear schoolYearidSchoolYear) {
        this.schoolYearidSchoolYear = schoolYearidSchoolYear;
    }

    public Studysubject getStudySubjectidStudySubject() {
        return studySubjectidStudySubject;
    }

    public void setStudySubjectidStudySubject(Studysubject studySubjectidStudySubject) {
        this.studySubjectidStudySubject = studySubjectidStudySubject;
    }

    public Users getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(Users teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public Users getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(Users studentLogin) {
        this.studentLogin = studentLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResults != null ? idResults.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Results)) {
            return false;
        }
        Results other = (Results) object;
        if ((this.idResults == null && other.idResults != null) || (this.idResults != null && !this.idResults.equals(other.idResults))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Results[ idResults=" + idResults + " ]";
    }
    
}
