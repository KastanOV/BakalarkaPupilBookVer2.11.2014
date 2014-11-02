/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneratedEntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KastanNotas
 */
@Entity
@Table(name = "teacher_subjects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeacherSubjects.findAll", query = "SELECT t FROM TeacherSubjects t"),
    @NamedQuery(name = "TeacherSubjects.findByIdTeacherSubjects", query = "SELECT t FROM TeacherSubjects t WHERE t.idTeacherSubjects = :idTeacherSubjects")})
public class TeacherSubjects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTeacher_Subjects")
    private Integer idTeacherSubjects;
    @JoinColumn(name = "StudySubject_idStudySubject", referencedColumnName = "idStudySubject")
    @ManyToOne(optional = false)
    private Studysubject studySubjectidStudySubject;
    @JoinColumn(name = "Users_Login", referencedColumnName = "Login")
    @ManyToOne(optional = false)
    private Users usersLogin;

    public TeacherSubjects() {
    }

    public TeacherSubjects(Integer idTeacherSubjects) {
        this.idTeacherSubjects = idTeacherSubjects;
    }

    public Integer getIdTeacherSubjects() {
        return idTeacherSubjects;
    }

    public void setIdTeacherSubjects(Integer idTeacherSubjects) {
        this.idTeacherSubjects = idTeacherSubjects;
    }

    public Studysubject getStudySubjectidStudySubject() {
        return studySubjectidStudySubject;
    }

    public void setStudySubjectidStudySubject(Studysubject studySubjectidStudySubject) {
        this.studySubjectidStudySubject = studySubjectidStudySubject;
    }

    public Users getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(Users usersLogin) {
        this.usersLogin = usersLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTeacherSubjects != null ? idTeacherSubjects.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeacherSubjects)) {
            return false;
        }
        TeacherSubjects other = (TeacherSubjects) object;
        if ((this.idTeacherSubjects == null && other.idTeacherSubjects != null) || (this.idTeacherSubjects != null && !this.idTeacherSubjects.equals(other.idTeacherSubjects))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneratedEntityClasses.TeacherSubjects[ idTeacherSubjects=" + idTeacherSubjects + " ]";
    }
    
}
