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

public class Parrentstudent implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idParrentStudent;

    private Users studentLogin;

    private Users parentLogin;

    public Parrentstudent() {
    }

    public Parrentstudent(Integer idParrentStudent) {
        this.idParrentStudent = idParrentStudent;
    }

    public Integer getIdParrentStudent() {
        return idParrentStudent;
    }

    public void setIdParrentStudent(Integer idParrentStudent) {
        this.idParrentStudent = idParrentStudent;
    }

    public Users getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(Users studentLogin) {
        this.studentLogin = studentLogin;
    }

    public Users getParentLogin() {
        return parentLogin;
    }

    public void setParentLogin(Users parentLogin) {
        this.parentLogin = parentLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParrentStudent != null ? idParrentStudent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parrentstudent)) {
            return false;
        }
        Parrentstudent other = (Parrentstudent) object;
        if ((this.idParrentStudent == null && other.idParrentStudent != null) || (this.idParrentStudent != null && !this.idParrentStudent.equals(other.idParrentStudent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Parrentstudent[ idParrentStudent=" + idParrentStudent + " ]";
    }
    
}
