/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Student;
import Entity.Studygroup;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface StudentsSBLocal {
    public Student studentLogin(String login, String password);
    public Student createNewUser(Student s);
    public Student saveUser(Student s);
    public Collection<Student> getAllStudents();
    public Collection<Student> getByParameters(String lastName, Date start, Date end, Boolean deleted);
    public Student getStudent(String UserId);
    public Collection<Student> getStudentByStudyGroup(Studygroup s);
    public List<Student> getStudents(String login, String password);
    public List<Student> getStudents(String login, String password, int StudyGroupId);
    public List<servicesDTO.StudySubject> getStudySubjects(String login);
    public List<servicesDTO.Results> getResultsByStudent(String login, String password);
    
}
