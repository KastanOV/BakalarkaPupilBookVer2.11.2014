/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testvytizenipopapu;

/**
 *
 * @author Jaroslav
 */
public class User {
    private String Login;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    private String Password;
    private String Role;
    private Integer StudyGroup;

    public Integer getStudyGroup() {
        return StudyGroup;
    }

    public void setStudyGroup(Integer StudyGroup) {
        this.StudyGroup = StudyGroup;
    }
}
