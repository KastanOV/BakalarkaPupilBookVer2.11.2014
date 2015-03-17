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

public class Sheduleitem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idSheduleItem;

    private short day;

    private short hour;

    private Studygroup studyGroupidStudyGroup;

    private Studysubject studySubjectidStudySubject;

    private Users usersLogin;

    public Sheduleitem() {
    }

    public Sheduleitem(Integer idSheduleItem) {
        this.idSheduleItem = idSheduleItem;
    }

    public Sheduleitem(Integer idSheduleItem, short day, short hour) {
        this.idSheduleItem = idSheduleItem;
        this.day = day;
        this.hour = hour;
    }

    public Integer getIdSheduleItem() {
        return idSheduleItem;
    }

    public void setIdSheduleItem(Integer idSheduleItem) {
        this.idSheduleItem = idSheduleItem;
    }

    public short getDay() {
        return day;
    }

    public void setDay(short day) {
        this.day = day;
    }

    public short getHour() {
        return hour;
    }

    public void setHour(short hour) {
        this.hour = hour;
    }

    public Studygroup getStudyGroupidStudyGroup() {
        return studyGroupidStudyGroup;
    }

    public void setStudyGroupidStudyGroup(Studygroup studyGroupidStudyGroup) {
        this.studyGroupidStudyGroup = studyGroupidStudyGroup;
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
        hash += (idSheduleItem != null ? idSheduleItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sheduleitem)) {
            return false;
        }
        Sheduleitem other = (Sheduleitem) object;
        if ((this.idSheduleItem == null && other.idSheduleItem != null) || (this.idSheduleItem != null && !this.idSheduleItem.equals(other.idSheduleItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Sheduleitem[ idSheduleItem=" + idSheduleItem + " ]";
    }
    
}
