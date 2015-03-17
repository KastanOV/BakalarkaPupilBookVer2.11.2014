/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;


public class Informations implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idinformations;
    private String description;

    private boolean infoForParrents;

    private String someMessage;

    private Date createDate;

    private Studygroup studyGroupidStudyGroup;

    private Users usersLogin;

    private Users teacherLogin;

    public Informations() {
    }

    public Informations(Integer idinformations) {
        this.idinformations = idinformations;
    }

    public Informations(Integer idinformations, String description, boolean infoForParrents, String someMessage, Date createDate) {
        this.idinformations = idinformations;
        this.description = description;
        this.infoForParrents = infoForParrents;
        this.someMessage = someMessage;
        this.createDate = createDate;
    }

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

    public boolean getInfoForParrents() {
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

    public Studygroup getStudyGroupidStudyGroup() {
        return studyGroupidStudyGroup;
    }

    public void setStudyGroupidStudyGroup(Studygroup studyGroupidStudyGroup) {
        this.studyGroupidStudyGroup = studyGroupidStudyGroup;
    }

    public Users getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(Users usersLogin) {
        this.usersLogin = usersLogin;
    }

    public Users getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(Users teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinformations != null ? idinformations.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informations)) {
            return false;
        }
        Informations other = (Informations) object;
        if ((this.idinformations == null && other.idinformations != null) || (this.idinformations != null && !this.idinformations.equals(other.idinformations))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Informations[ idinformations=" + idinformations + " ]";
    }
    
}
