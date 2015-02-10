/*
 * Copyright (C) 2015 Topr
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package managedBeans;

import Entity.Users;
import SessionBeans.UsersSBLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Topr
 */
@ManagedBean
@SessionScoped
public class PasswordsController {

    @EJB
    private UsersSBLocal usersSB;
    
    private Boolean onlyTeachers;
    
    private Boolean onlyDeleted;

    private String searchLastName;
    private Users editedUser;
    

    
    /**
     * Creates a new instance of PasswordsController
     */
    public PasswordsController() {
        onlyDeleted = false;
        onlyTeachers = false;
        searchLastName = "";
    }
    
    public Collection<Users> getUsers(){
        return usersSB.getUsers(onlyTeachers, onlyDeleted, searchLastName);
    }
    
    
    public Boolean getOnlyDeleted() {
        return onlyDeleted;
    }

    public void setOnlyDeleted(Boolean onlyDeleted) {
        this.onlyDeleted = onlyDeleted;
    }

    public Boolean getOnlyTeachers() {
        return onlyTeachers;
    }

    public void setOnlyTeachers(Boolean onlyTeachers) {
        this.onlyTeachers = onlyTeachers;
    }

    public String getSearchLastName() {
        return searchLastName;
    }

    public void setSearchLastName(String searchLastName) {
        this.searchLastName = searchLastName;
    }
    public Users getEditedUser() {
        return editedUser;
    }

    public void setEditedUser(Users editedUser) {
        this.editedUser = editedUser;
    }
    public String getLegend(){
        if(onlyTeachers) return "Vybraní učitelé";
        else return "Vybraní studenti";
    }
}
