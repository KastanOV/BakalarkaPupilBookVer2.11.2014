/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Teacher;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
@Singleton
public class TeachersSessionBean implements TeachersSessionBeanLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Teacher checkLogin(String login, String password){
        
        try{
            return (Teacher) em.createNamedQuery("Teacher.checkLogin")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();     
        } catch (Exception e){
            return null;
        }
        
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
