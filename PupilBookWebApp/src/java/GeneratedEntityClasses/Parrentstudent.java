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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KastanNotas
 */
@Entity
@Table(name = "parrentstudent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parrentstudent.findAll", query = "SELECT p FROM Parrentstudent p"),
    @NamedQuery(name = "Parrentstudent.findByIdParrentStudent", query = "SELECT p FROM Parrentstudent p WHERE p.idParrentStudent = :idParrentStudent")})
public class Parrentstudent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idParrentStudent")
    private Integer idParrentStudent;
    @JoinColumn(name = "Student_Login", referencedColumnName = "Login")
    @ManyToOne(optional = false)
    private Users studentLogin;
    @JoinColumn(name = "Parent_Login", referencedColumnName = "Login")
    @ManyToOne(optional = false)
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
        return "GeneratedEntityClasses.Parrentstudent[ idParrentStudent=" + idParrentStudent + " ]";
    }
    
}
