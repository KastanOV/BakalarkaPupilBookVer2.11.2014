/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KastanNotas
 */
@Entity
@Table(name = "results")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Results.findAll", query = "SELECT r FROM Results r"),
    @NamedQuery(name = "Results.findByIdResults", query = "SELECT r FROM Results r WHERE r.idResults = :idResults"),
    @NamedQuery(name = "Results.findByDescription", query = "SELECT r FROM Results r WHERE r.description = :description"),
    @NamedQuery(name = "Results.findByScore", query = "SELECT r FROM Results r WHERE r.score = :score"),
    @NamedQuery(name = "Results.findByDate", query = "SELECT r FROM Results r WHERE r.date = :date")})
public class Results implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idResults")
    private Integer idResults;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Score")
    private short score;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "SchoolYear_idSchoolYear", referencedColumnName = "idSchoolYear")
    @ManyToOne(optional = false)
    private Schoolyear schoolYearidSchoolYear;
    @JoinColumn(name = "StudySubject_idStudySubject", referencedColumnName = "idStudySubject")
    @ManyToOne(optional = false)
    private Studysubject studySubjectidStudySubject;
    @JoinColumn(name = "Teacher_Login", referencedColumnName = "Login")
    @ManyToOne(optional = false)
    private Users teacherLogin;
    @JoinColumn(name = "Student_Login", referencedColumnName = "Login")
    @ManyToOne(optional = false)
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
    public Schoolyear getSchoolYearidSchoolYear() {
        return schoolYearidSchoolYear;
    }

    public void setSchoolYearidSchoolYear(Schoolyear schoolYearidSchoolYear) {
        this.schoolYearidSchoolYear = schoolYearidSchoolYear;
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
