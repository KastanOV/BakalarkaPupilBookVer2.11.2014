/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneratedSessionBeans;

import GeneratedEntityClasses.Informations;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class InformationsFacade extends AbstractFacade<Informations> {
    @PersistenceContext(unitName = "PupilBookWebAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformationsFacade() {
        super(Informations.class);
    }
    
}