/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Users;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class StudentsSB implements StudentsSBLocal {
    @PersistenceContext
    private EntityManager em;
    
    private DAOFactory factory;
    
    private DAOFactory getFactory(){
        if(factory == null){
            factory = new DAOFactoryJPA(em);
        }
        return factory;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Users createNewUser(Users s) {
        return getFactory().getStudentsDAO().createNewUser(s);
    }

    @Override
    public Users saveNewStudent(Users s) {
        return getFactory().getStudentsDAO().saveStudent(s);
    }

    @Override
    public Collection<Users> getAllStudents() {
        return getFactory().getStudentsDAO().getAllStudents();
    }
}
