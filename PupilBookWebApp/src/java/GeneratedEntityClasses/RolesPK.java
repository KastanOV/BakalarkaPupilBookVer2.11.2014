/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneratedEntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author KastanNotas
 */
@Embeddable
public class RolesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Role")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Users_Login")
    private String usersLogin;

    public RolesPK() {
    }

    public RolesPK(String role, String usersLogin) {
        this.role = role;
        this.usersLogin = usersLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(String usersLogin) {
        this.usersLogin = usersLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (role != null ? role.hashCode() : 0);
        hash += (usersLogin != null ? usersLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPK)) {
            return false;
        }
        RolesPK other = (RolesPK) object;
        if ((this.role == null && other.role != null) || (this.role != null && !this.role.equals(other.role))) {
            return false;
        }
        if ((this.usersLogin == null && other.usersLogin != null) || (this.usersLogin != null && !this.usersLogin.equals(other.usersLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneratedEntityClasses.RolesPK[ role=" + role + ", usersLogin=" + usersLogin + " ]";
    }
    
}
