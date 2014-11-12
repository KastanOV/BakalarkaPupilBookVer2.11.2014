/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KastanNotas
 */
@Entity
@Table(name = "attendance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT a FROM Attendance a"),
    @NamedQuery(name = "Attendance.findByIdAttendance", query = "SELECT a FROM Attendance a WHERE a.idAttendance = :idAttendance"),
    @NamedQuery(name = "Attendance.findByMissingStart", query = "SELECT a FROM Attendance a WHERE a.missingStart = :missingStart"),
    @NamedQuery(name = "Attendance.findByMissingEnd", query = "SELECT a FROM Attendance a WHERE a.missingEnd = :missingEnd"),
    @NamedQuery(name = "Attendance.findByExcussed", query = "SELECT a FROM Attendance a WHERE a.excussed = :excussed")})
public class Attendance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAttendance")
    private Integer idAttendance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MissingStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date missingStart;
    @Column(name = "MissingEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date missingEnd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Excussed")
    private boolean excussed;
    @JoinColumn(name = "Users_Login", referencedColumnName = "Login")
    @ManyToOne(optional = false)
    private Users usersLogin;

    public Attendance() {
    }

    public Attendance(Integer idAttendance) {
        this.idAttendance = idAttendance;
    }

    public Attendance(Integer idAttendance, Date missingStart, boolean excussed) {
        this.idAttendance = idAttendance;
        this.missingStart = missingStart;
        this.excussed = excussed;
    }

    public Integer getIdAttendance() {
        return idAttendance;
    }

    public void setIdAttendance(Integer idAttendance) {
        this.idAttendance = idAttendance;
    }

    public Date getMissingStart() {
        return missingStart;
    }

    public void setMissingStart(Date missingStart) {
        this.missingStart = missingStart;
    }

    public Date getMissingEnd() {
        return missingEnd;
    }

    public void setMissingEnd(Date missingEnd) {
        this.missingEnd = missingEnd;
    }

    public boolean getExcussed() {
        return excussed;
    }

    public void setExcussed(boolean excussed) {
        this.excussed = excussed;
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
        hash += (idAttendance != null ? idAttendance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.idAttendance == null && other.idAttendance != null) || (this.idAttendance != null && !this.idAttendance.equals(other.idAttendance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Attendance[ idAttendance=" + idAttendance + " ]";
    }
    
}
