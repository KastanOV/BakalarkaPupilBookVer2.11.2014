/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneratedSessionBeans;

import GeneratedEntityClasses.Schoolyear;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class SchoolyearFacade extends AbstractFacade<Schoolyear> {
    @PersistenceContext(unitName = "PupilBookWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SchoolyearFacade() {
        super(Schoolyear.class);
    }
    
}