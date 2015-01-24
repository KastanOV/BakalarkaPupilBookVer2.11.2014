/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "sheduleitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sheduleitem.findAll", query = "SELECT s FROM Sheduleitem s"),
    @NamedQuery(name = "Sheduleitem.findByIdSheduleItem", query = "SELECT s FROM Sheduleitem s WHERE s.idSheduleItem = :idSheduleItem"),
    @NamedQuery(name = "Sheduleitem.findByDay", query = "SELECT s FROM Sheduleitem s WHERE s.day = :day"),
    @NamedQuery(name = "Sheduleitem.findByHour", query = "SELECT s FROM Sheduleitem s WHERE s.hour = :hour")})
public class Sheduleitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSheduleItem")
    private Integer idSheduleItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day")
    private short day;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hour")
    private short hour;
    @JoinColumn(name = "StudyGroup_idStudyGroup", referencedColumnName = "idStudyGroup")
    @ManyToOne(optional = false)
    private Studygroup studyGroupidStudyGroup;
    @JoinColumn(name = "StudySubject_idStudySubject", referencedColumnName = "idStudySubject")
    @ManyToOne
    private Studysubject studySubjectidStudySubject;
    @JoinColumn(name = "Users_Login", referencedColumnName = "Login")
    @ManyToOne
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
