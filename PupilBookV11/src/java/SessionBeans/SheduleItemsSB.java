/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Sheduleitem;
import java.util.List;
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
    @Override
    public List<Sheduleitem> getSheduleItems(String login, String password) {
        
        if(checkUser(login, password)){
            return em.createNativeQuery("SELECT * FROM SheduleItem"
                    + " left join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup"
                    + " join schoolyear on schoolyear.idSchoolYear = studygroup.SchoolYear_idSchoolYear"
                    + " WHERE Users_Login = ?login AND schoolyear.isactualyear = true", Sheduleitem.class)
                    .setParameter("login", login)
                    .getResultList();
        }else {
            return null;
        }
    }
    
    private boolean checkUser(String login, String password){
        long tmp = (long)em.createNativeQuery("SELECT count(*) FROM Users u WHERE u.login = ?login AND u.password = ?password")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
        if(tmp > 0){
            return true;
        }else return false;
    }
    
}
