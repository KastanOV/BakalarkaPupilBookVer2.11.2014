/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
@Stateless
public class SchoolYearSB implements SchoolYearSBLocal {

    @PersistenceContext
    private EntityManager em;
    
    private DAOFactory factory;
    
    private DAOFactory getFactory(){
        if(factory == null){
            factory = new DAOFactoryJPA(em);
        }
        return factory;
    }
    
    @Override
    public Schoolyear saveSchoolyear(Schoolyear s) {
        return getFactory().getSchoolYearDAO().saveSchoolyear(s);
    }
    @Override
    public List<Schoolyear> getAllSchoolYears() {
        return getFactory().getSchoolYearDAO().getAllSchoolYears();
    }
    @Override
    public Schoolyear getSchoolyear(int id) {
        return getFactory().getSchoolYearDAO().getSchoolyear(id);
    }
    @Override
    public void deleteSchooYear(Schoolyear s) {
        getFactory().getSchoolYearDAO().deleteSchooYear(s);
    }
    @Override
    public void deleteSchooYear(int id) {
        getFactory().getSchoolYearDAO().deleteSchooYear(id);
    }

    @Override
    public Schoolyear getActualSchoolyear() {
        return getFactory().getSchoolYearDAO().getActualSchoolYear();
    }
    
    
}
