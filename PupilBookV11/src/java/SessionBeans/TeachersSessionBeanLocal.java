/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Sheduleitem;
import Entity.Student;
import Entity.Studygroup;
import Entity.Teacher;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface TeachersSessionBeanLocal {
   public Teacher checkLogin(String login, String password);
   
   public List<Sheduleitem> getSheduleItems(String login, String password);
   public List<Studygroup> getStudyGroups(String login, String password);
   public List<Student> getStudents(String login, String password);
}
