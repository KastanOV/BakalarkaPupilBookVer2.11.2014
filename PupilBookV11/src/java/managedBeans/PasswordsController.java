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

import Entity.Studygroup;
import Entity.Users;
import SessionBeans.PasswordReset;
import SessionBeans.StudyGroupsSBLocal;
import SessionBeans.UsersSBLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Topr
 */
@ManagedBean
@SessionScoped
public class PasswordsController {

    @EJB
    private UsersSBLocal usersSB;
    @EJB
    private StudyGroupsSBLocal sgSB;
    private Boolean onlyTeachers;
    private Boolean onlyDeleted;
    private String searchLastName;
    private Users editedUser;
    private Collection<Users> selectedUsers;
    private Studygroup selectedStudyGroup;
    private Collection<PasswordReset> newPasswords;

       

    /**
     * Creates a new instance of PasswordsController
     */
    public PasswordsController() {
        onlyDeleted = false;
        onlyTeachers = false;
        searchLastName = "";
        selectedUsers = new ArrayList<>();
    }
    
    public void changePasswords() {
        
        newPasswords = new ArrayList<>();
        for(Users u : selectedUsers){
            Users parent = usersSB.findParrent(u);
            PasswordReset tmp = new PasswordReset();
            tmp.setLoginStudent(u.getLogin());
            tmp.setLoginParrent(parent.getLogin());

            String tmpPassword = createPassword();
            tmp.setPasswordParrent(tmpPassword);
            parent.setPassword(tmpPassword);
            usersSB.saveUser(parent);

            tmpPassword = createPassword();
            tmp.setPasswordStudent(tmpPassword);
            u.setPassword(tmpPassword);
            usersSB.saveUser(u);

            newPasswords.add(tmp);
        }
            
            selectedUsers = new ArrayList<>();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ShowNewCredentials.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PasswordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public Collection<PasswordReset> getNewPasswords() {
        return newPasswords;
    }

    public void setNewPasswords(Collection<PasswordReset> newPasswords) {
        this.newPasswords = newPasswords;
    }
    
    public Collection<Users> getUsers(){
        return usersSB.getUsers(onlyTeachers, onlyDeleted, searchLastName, selectedStudyGroup);
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
        
        selectedUsers.add(editedUser);
        
    }
    public String getLegend(){
        if(onlyTeachers) return "Vybraní učitelé";
        else return "Vybraní studenti";
    }
    public Collection<Users> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(Collection<Users> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }
    public void deleteFromSelectedUser(Users u){
        selectedUsers.remove(u);
    }
    public Studygroup getSelectedStudyGroup() {
        return selectedStudyGroup;
    }

    public void setSelectedStudyGroup(Studygroup selectedStudyGroup) {
        this.selectedStudyGroup = selectedStudyGroup;
    }
    public List<Studygroup> getStudyGroups(){
        return sgSB.getActualStudyGroups();
    }
    
    private String createPassword(){
        char[] symbols;
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        for (char ch = 'A'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
        Random random = new Random();
        
        StringBuilder value = new StringBuilder();

        for (int idx = 0; idx < 10; ++idx) 
            value.append(symbols[random.nextInt(symbols.length)]);
        return value.toString();
    }
}
