/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Sheduleitem;
import Entity.Studygroup;
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
public class SheduleItemsSB implements SheduleItemsSBLocal {
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
    public void insertNewSheduleItem(Sheduleitem s) {
        getFactory().getSheduleItemsDAO().insertNewSheduleItem(s);
    }

    @Override
    public Sheduleitem saveSheduleItem(Sheduleitem s) {
        
        return getFactory().getSheduleItemsDAO().saveSheduleItem(s);
    }
    @Override
    public List<Sheduleitem> getSheduleItems(String login, String password) {
        return getFactory().getSheduleItemsDAO().getSheduleItems(login, password);
    }

    @Override
    public void refreshTable() {
        getFactory().refreshEntityManager();
    }

    @Override
    public List<Sheduleitem> getSheduleItems(Studygroup sg) {
        return getFactory().getSheduleItemsDAO().getSheduleItems(sg);
    }
}
