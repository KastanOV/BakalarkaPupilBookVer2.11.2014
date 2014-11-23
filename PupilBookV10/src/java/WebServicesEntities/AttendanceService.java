/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServicesEntities;


import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Topr
 */
@Entity
public class AttendanceService implements Serializable{
    @Id
    @Column(name = "idAttendance")
    private Integer idAttendance;

    @Column(name = "MissingStart")
    private Date missingStart;
    @Column(name = "MissingEnd")
    private Date missingEnd;
    @Column(name = "Excussed")
    private boolean excussed;
    @Column(name = "Users_Login")
    private String usersLogin;
    
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

    public boolean isExcussed() {
        return excussed;
    }

    public void setExcussed(boolean excussed) {
        this.excussed = excussed;
    }

    public String getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(String usersLogin) {
        this.usersLogin = usersLogin;
    }
    
}
