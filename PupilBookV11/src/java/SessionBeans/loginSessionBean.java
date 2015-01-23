    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Admin;
import Entity.Teacher;
import Entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless (mappedName="loginSessionBean")
public class loginSessionBean implements loginSessionBeanLocal {
    @PersistenceContext
    private EntityManager em;
        
    @Override
    public Users doLogin(Users u){
        try{
            Admin loaded = (Admin) em.createNamedQuery("Admin.doLogin")
                .setParameter("login", u.getLogin())
                .setParameter("password", u.getPassword())
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            
        }
        try{
            Teacher loaded = (Teacher) em.createNamedQuery("Teacher.doLogin")
                .setParameter("login", u.getLogin())
                .setParameter("password", u.getPassword())
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            return u;
        }
    }
}
