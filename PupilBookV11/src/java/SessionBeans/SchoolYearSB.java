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
        
    @Override
    public Schoolyear saveSchoolyear(Schoolyear s) {
        if(s.getIsactualyear() == true){
            for(Schoolyear si : getAllSchoolYears()){
                si.setIsactualyear(false);
            }
        }
        if(s.getIdSchoolYear() != null){
            em.merge(s);
        } else {
            em.persist(s);
        }
        em.flush();
        return s;
    }
    @Override
    public List<Schoolyear> getAllSchoolYears() {
        return em.createNamedQuery("Schoolyear.findAll").getResultList();
    }
    @Override
    public Schoolyear getSchoolyear(int id) {
        return em.find(Schoolyear.class, id);
    }
    @Override
    public void deleteSchooYear(Schoolyear s) {
        em.remove(em.find(Schoolyear.class, s.getIdSchoolYear()));
    }
    @Override
    public void deleteSchooYear(int id) {
        em.remove(em.find(Schoolyear.class, id));
    }

    @Override
    public Schoolyear getActualSchoolyear() {
        Schoolyear idActualYear = (Schoolyear) em.createNativeQuery("SELECT * FROM schoolyear WHERE schoolyear.isactualyear = true", Schoolyear.class)
                .getSingleResult();
        return idActualYear;
    }
    
    
}
