
package SessionBeans;

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
    
    @Override
    public Schoolyear saveSchoolyear(Schoolyear s) {
        if(s.getIdSchoolYear() != null){
            em.merge(s);
        } else {
            em.persist(s);
        }
        em.flush();
        return s;
    }

    @Override
    public List<Schoolyear> getAllSchoolYears() {
        return em.createNamedQuery("Schoolyear.findAll").getResultList();
    }

    @Override
    public Schoolyear getSchoolyear(int id) {
        return em.find(Schoolyear.class, id);
    }

    @Override
    public void deleteSchooYear(Schoolyear s) {
        em.remove(em.find(Schoolyear.class, s.getIdSchoolYear()));
    }

    @Override
    public void deleteSchooYear(int id) {
        em.remove(em.find(Schoolyear.class, id));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
