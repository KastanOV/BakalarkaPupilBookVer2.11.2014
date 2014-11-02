/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneratedSessionBeans;

import GeneratedEntityClasses.Sheduleitem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class SheduleitemFacade extends AbstractFacade<Sheduleitem> {
    @PersistenceContext(unitName = "PupilBookWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SheduleitemFacade() {
        super(Sheduleitem.class);
    }
    
}