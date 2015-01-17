/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Sheduleitem;
import Entity.Studygroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface SheduleItemsSBLocal {
    public void insertNewSheduleItem(Sheduleitem s);
    public void refreshTable();
    public Sheduleitem saveSheduleItem(Sheduleitem s);
    public List<Sheduleitem> getSheduleItems(String login, String password);
    public List<Sheduleitem> getSheduleItems(Studygroup sg);
}
