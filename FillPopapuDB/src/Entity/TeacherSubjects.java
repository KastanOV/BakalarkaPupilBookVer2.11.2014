/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;


/**
 *
 * @author Topr
 */

public class TeacherSubjects implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idTeacherSubjects;

    private Studysubject studySubjectidStudySubject;

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
        return "Entity.TeacherSubjects[ idTeacherSubjects=" + idTeacherSubjects + " ]";
    }
    
}
