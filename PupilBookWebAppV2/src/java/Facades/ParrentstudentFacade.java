/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entity.Parrentstudent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class ParrentstudentFacade extends AbstractFacade<Parrentstudent> {
    @PersistenceContext(unitName = "PupilBookWebAppV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParrentstudentFacade() {
        super(Parrentstudent.class);
    }
    
}
