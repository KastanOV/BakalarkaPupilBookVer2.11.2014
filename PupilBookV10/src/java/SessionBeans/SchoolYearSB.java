
package SessionBeans;

import dao.*;
import Entity.Schoolyear;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author KastanNotas
 */
@Stateless
public class SchoolYearSB implements SchoolYearSBLocal {

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
    public Schoolyear saveSchoolyear(Schoolyear s) {
        getFactory().getSchoolYearDAO().saveSchoolyear(s);
        return s;
    }

    @Override
    public List<Schoolyear> getAllSchoolYears() {
        return getFactory().getSchoolYearDAO().getAllSchoolYears();
    }

    @Override
    public Schoolyear getSchoolyear(int id) {
        return getFactory().getSchoolYearDAO().getSchoolyear(id);
    }

    @Override
    public void deleteSchooYear(Schoolyear s) {
        getFactory().getSchoolYearDAO().deleteSchooYear(s);
    }

    @Override
    public void deleteSchooYear(int id) {
        getFactory().getSchoolYearDAO().deleteSchooYear(id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
