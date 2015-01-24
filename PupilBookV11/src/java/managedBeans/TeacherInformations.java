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

import Entity.Informations;
import Entity.Student;
import Entity.Studygroup;
import Entity.Teacher;
import SessionBeans.InformationSBLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Topr
 */
@ManagedBean
@ViewScoped
public class TeacherInformations implements Serializable{
    
    @EJB
    private InformationSBLocal informationSB;
    private Teacher loggedUser;
    private Informations editedInformation;
    private Student selectedStudent;
    private Studygroup selectedStudygroup;
    

    /**
     * Creates a new instance of TeacherInformations
     */
    public TeacherInformations() {
    }
    
    public List<Informations> getInformations(){
        List<Informations> tmp = informationSB.getInformations(loggedUser);
        return informationSB.getInformations(loggedUser);
    }
    public Informations prepareInformation(Boolean b){
        editedInformation = new Informations();
        editedInformation.setUsersLogin(selectedStudent);
        editedInformation.setInfoForParrents(b);
        editedInformation.setTeacherLogin(loggedUser);
        editedInformation.setCreateDate(new Date());
        return editedInformation;
    }
    public Informations prepareInformation(){
        editedInformation = new Informations();
        editedInformation.setStudyGroupidStudyGroup(selectedStudygroup);
        editedInformation.setTeacherLogin(loggedUser);
        editedInformation.setCreateDate(new Date());
        return editedInformation;
    }
    public void saveInformation(){
        try{
            informationSB.saveInformation(editedInformation);
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodařilo se uložit data do databáze.", 
					"Nepodařilo se uložit data do databáze.. Příčina: " + e.getMessage()));
		}
        
    }
    
    public Teacher getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Teacher loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Informations getEditedInformation() {
        return editedInformation;
    }

    public void setEditedInformation(Informations editedinformation) {
        this.editedInformation = editedinformation;
    }
    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public Studygroup getSelectedStudygroup() {
        return selectedStudygroup;
    }

    public void setSelectedStudygroup(Studygroup selectedStudygroup) {
        this.selectedStudygroup = selectedStudygroup;
    }
}
