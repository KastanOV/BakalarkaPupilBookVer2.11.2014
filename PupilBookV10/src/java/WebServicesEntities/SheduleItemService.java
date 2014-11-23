/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServicesEntities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author Topr
 */
@Entity

public class SheduleItemService implements Serializable{

    @Column(name = "idSheduleItem")
    @Id
    private Integer idSheduleItem;
    @Column(name = "day")
    private short day;
    @Column(name = "hour")
    private short hour;
    @Column(name = "StudyGroup_idStudyGroup")
    private int studyGroupidStudyGroup;
    @Column(name = "StudySubject_idStudySubject")
    private int studySubjectidStudySubject;
    @Column(name = "Users_Login")
    private String usersLogin;

    public int getIdSheduleItem() {
        return idSheduleItem;
    }

    public void setIdSheduleItem(int idSheduleItem) {
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

    public int getStudyGroupidStudyGroup() {
        return studyGroupidStudyGroup;
    }

    public void setStudyGroupidStudyGroup(int studyGroupidStudyGroup) {
        this.studyGroupidStudyGroup = studyGroupidStudyGroup;
    }

    public int getStudySubjectidStudySubject() {
        return studySubjectidStudySubject;
    }

    public void setStudySubjectidStudySubject(int studySubjectidStudySubject) {
        this.studySubjectidStudySubject = studySubjectidStudySubject;
    }

    public String getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(String usersLogin) {
        this.usersLogin = usersLogin;
    }
    
}
