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
public class Attendance implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idAttendance;

    private Date missingStart;

    private Date missingEnd;

    private boolean excussed;

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
        return "Entity.Attendance[ idAttendance=" + idAttendance + " ]";
    }
    
}
