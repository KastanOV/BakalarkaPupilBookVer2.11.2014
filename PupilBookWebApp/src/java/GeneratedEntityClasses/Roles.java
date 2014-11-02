/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneratedEntityClasses;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KastanNotas
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByRole", query = "SELECT r FROM Roles r WHERE r.rolesPK.role = :role"),
    @NamedQuery(name = "Roles.findByUsersLogin", query = "SELECT r FROM Roles r WHERE r.rolesPK.usersLogin = :usersLogin")})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesPK rolesPK;
    @JoinColumn(name = "Users_Login", referencedColumnName = "Login", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Roles() {
    }

    public Roles(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public Roles(String role, String usersLogin) {
        this.rolesPK = new RolesPK(role, usersLogin);
    }

    public RolesPK getRolesPK() {
        return rolesPK;
    }

    public void setRolesPK(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesPK != null ? rolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.rolesPK == null && other.rolesPK != null) || (this.rolesPK != null && !this.rolesPK.equals(other.rolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneratedEntityClasses.Roles[ rolesPK=" + rolesPK + " ]";
    }
    
}
