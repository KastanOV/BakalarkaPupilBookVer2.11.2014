/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import Entity.Teacher;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface SchoolYearSBLocal {
    
    public Schoolyear saveSchoolyear(Schoolyear s);
    public List<Schoolyear> getAllSchoolYears();
    public Schoolyear getSchoolyear(int id);
    public void deleteSchooYear(Schoolyear s);
    public void deleteSchooYear(int id);
    
}
