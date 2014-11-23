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
public class InformationService implements Serializable{
    @Id
    @Column(name = "idinformations")
    private Integer idinformations;
    @Column(name = "Description")
    private String description;
    @Column(name = "InfoForParrents")
    private boolean infoForParrents;
    @Column(name = "SomeMessage")
    private String someMessage;
    @Column(name = "CreateDate")
    private Date createDate;
    @Column(name = "StudyGroup_idStudyGroup")
    private int studyGroupidStudyGroup;
    @Column(name = "Users_Login")
    private String usersLogin;

    public Integer getIdinformations() {
        return idinformations;
    }

    public void setIdinformations(Integer idinformations) {
        this.idinformations = idinformations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInfoForParrents() {
        return infoForParrents;
    }

    public void setInfoForParrents(boolean infoForParrents) {
        this.infoForParrents = infoForParrents;
    }

    public String getSomeMessage() {
        return someMessage;
    }

    public void setSomeMessage(String someMessage) {
        this.someMessage = someMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStudyGroupidStudyGroup() {
        return studyGroupidStudyGroup;
    }

    public void setStudyGroupidStudyGroup(int studyGroupidStudyGroup) {
        this.studyGroupidStudyGroup = studyGroupidStudyGroup;
    }

    public String getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(String usersLogin) {
        this.usersLogin = usersLogin;
    }
}
