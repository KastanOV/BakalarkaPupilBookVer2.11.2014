/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Sheduleitem;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Users;
import SessionBeans.SheduleItemsSBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Topr
 */
@ManagedBean
@RequestScoped
public class TeacherSheduleController {
    private List<Sheduleitem> selectedSheduleItems;
    private Users loggedUser;
    
    
    @EJB
    private SheduleItemsSBLocal sheduleItemsSB;
    
    /**
     * Creates a new instance of TeacherSheduleController
     */
    public TeacherSheduleController() {
    }
    
    public Sheduleitem getSheduleitem(short day, short hour){
        if(selectedSheduleItems == null){
            selectedSheduleItems = sheduleItemsSB.getSheduleItems(loggedUser.getLogin(), loggedUser.getPassword());
        } 
        for (Sheduleitem item : selectedSheduleItems){
            if(item.getDay() == day && item.getHour() == hour) 
                return item;
        }
        Sheduleitem free = new Sheduleitem();
        free.setStudyGroupidStudyGroup(new Studygroup(null, ""));
        free.setStudySubjectidStudySubject(new Studysubject(null, "Volná hodina", null));
        return free;
    }
    
    public List<Sheduleitem> getSelectedSheduleItems() {
        return selectedSheduleItems;
    }

    public void setSelectedSheduleItems(List<Sheduleitem> selectedSheduleItems) {
        this.selectedSheduleItems = selectedSheduleItems;
    }
    
    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }
}
