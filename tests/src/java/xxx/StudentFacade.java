/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xxx;

import Facades.AbstractFacade;
import xxx.Student;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
/**
 *
 * @author KastanNotas
 */
@Stateless
public class StudentFacade extends AbstractFacade<Student>{

    @PersistenceContext(unitName = "PupilBookWebAppPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    public StudentFacade() {
        super(Student.class);
    }

    /**
     *
     * @param entityclass
     */
    public StudentFacade(Class<Student> entityclass) {
        super(entityclass);
    }
 
}
