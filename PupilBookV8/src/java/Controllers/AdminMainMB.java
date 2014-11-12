/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Schoolyear;
import SessionBeans.SchoolyearFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 *
 * @author KastanNotas
 */
@ManagedBean
@SessionScoped
public class AdminMainMB implements Serializable{
    @EJB
    private SchoolyearFacadeLocal schoolyear;
    
    private List<SelectItem> Years2;
    private Schoolyear editedSY;

    public Schoolyear getEditedSY() {
        System.out.print("Test");
        return editedSY;
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public Schoolyear prepareCreateSY(){
        
        editedSY = new Schoolyear();
        initializeEmbeddableKey();
        System.out.println("Prepare created");
        return editedSY;
    }
    public void saveNewSchoolYear(){
		try {
			schoolyear.create(editedSY);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepoda�ilo se ulo�it data do datab�ze.", 
					"Nepoda�ilo se ulo�it data do datab�ze. P���ina: " + e.getMessage()));
			e.printStackTrace();
		}
	}
    public void setEditedSY(Schoolyear editedSY) {
        this.editedSY = editedSY;
    }
    
    /**
     * Creates a new instance of adminController
     */
    public AdminMainMB() {
    }
    
    public List<SelectItem> getYears2(){
        Years2 = new ArrayList<>();
        List<Schoolyear> sy = schoolyear.findAll();
        sy.stream().forEach((item) -> {
            Years2.add(new SelectItem(item, item.getName()));
        });
        return Years2;
    }
    public void setYears2(List<SelectItem> Years2) {
        this.Years2 = Years2;
    }
    
}

