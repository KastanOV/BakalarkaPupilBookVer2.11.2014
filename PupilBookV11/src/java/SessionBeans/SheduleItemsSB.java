/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Sheduleitem;
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
    
    @Override
    public void insertNewSheduleItem(Sheduleitem s) {
        em.persist(s);
    }

    @Override
    public Sheduleitem saveSheduleItem(Sheduleitem s) {
        em.merge(s);
        return s;
    }
    
}
