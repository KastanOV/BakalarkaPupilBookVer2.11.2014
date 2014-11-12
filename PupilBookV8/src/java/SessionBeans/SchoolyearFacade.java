/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class SchoolyearFacade extends AbstractFacade<Schoolyear> implements SchoolyearFacadeLocal{
    @PersistenceContext(unitName = "PupilBookV8PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SchoolyearFacade() {
        super(Schoolyear.class);
    }

    @Override
    public Schoolyear getSchoolYear(int id) {
        return em.find(Schoolyear.class, id);
    }
    
}
