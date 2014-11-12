/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Studygroup;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless
public class StudyGroupSB implements StudyGroupSBLocal {

    @PersistenceContext
    private EntityManager em;
    
    private DAOFactory factory;
    
    private DAOFactory getFactory(){
        if(factory == null){
            factory = new DAOFactoryJPA(em);
        }
        return factory;
    }
    
    @Override
    public Studygroup saveStudygroup(Studygroup s) {
        return getFactory().getStudygroupDAO().saveStudygroup(s);
    }

    @Override
    public List<Studygroup> getAllStudygroup() {
        return getFactory().getStudygroupDAO().getAllStudygroup();
    }

    @Override
    public Studygroup getStudygroup(int StudygroupId) {
        return getFactory().getStudygroupDAO().getStudygroup(StudygroupId);
    }

    @Override
    public void deleteStudygroup(Studygroup p) {
        getFactory().getStudygroupDAO().deleteStudygroup(p);
    }

    @Override
    public void deleteStudygroup(int StudygroupId) {
        getFactory().getStudygroupDAO().deleteStudygroup(StudygroupId);
    }
        
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
}
