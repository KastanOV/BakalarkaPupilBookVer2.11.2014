/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import Entity.Studysubject;
import SessionBeans.AdminSheduleSessionBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class AdminSheduleController {
    @EJB
    AdminSheduleSessionBean sb;
    private Studysubject[][] edited;
    
    /**
     * Creates a new instance of AdminSheduleController
     */
    public AdminSheduleController() {
        
    }
    
    
}
