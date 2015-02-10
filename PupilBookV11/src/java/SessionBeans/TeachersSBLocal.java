/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Results;
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
public interface TeachersSBLocal {
    public Collection<Teacher> getAllTeachers();
    
    public Teacher getTeacher(String UserId);
    public Teacher saveTeacher(Teacher t);
    public Teacher createNewTeacher(Teacher t);
    public Collection<Teacher> getTeachersByStudyGroup(Studygroup s);
    public Teacher checkLogin(String login, String password);
    public List<Results> getResultsTeacher(String login, String password);
    public Collection<Teacher> getTeachersByAtributes(Boolean isDeleted, String lastName);
    public Collection<Teacher> getTeachersForShedule(short day, short hour);
    
}
