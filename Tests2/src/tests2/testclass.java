/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests2;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
public class testclass {
    @PersistenceContext
    private EntityManager em;
    
    public void getUsers(){
        List<Object[]> li = em.createNativeQuery("select si.idSheduleItem, si.day,si.hour,si.StudyGroup_idStudyGroup,si.StudySubject_idStudySubject from  sheduleitem si" +
                                "join schoolyear sy" +
                                "left join studygroup sg on si.StudyGroup_idStudyGroup = sg.idStudyGroup" +
                                "where sy.isactualyear = '1' and si.Users_Login = :login ")
                .setParameter("login", "CHL000")
                .getResultList();
        for(Object item : li){
            System.out.print(li.get(0).toString());
            System.out.print(li.get(1).toString());
            System.out.print(li.get(2).toString());
            System.out.print(li.get(3).toString());
        }
    }
}
