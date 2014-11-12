/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KastanNotas
 */
@Local
public interface SchoolyearFacadeLocal {

    public Schoolyear getSchoolYear(int id);
    
    void create(Schoolyear schoolyear);

    void edit(Schoolyear schoolyear);

    void remove(Schoolyear schoolyear);

    Schoolyear find(Object id);

    List<Schoolyear> findAll();

    List<Schoolyear> findRange(int[] range);

    int count();
    
}
