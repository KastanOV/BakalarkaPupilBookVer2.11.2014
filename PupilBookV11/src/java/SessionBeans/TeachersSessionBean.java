/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Sheduleitem;
import Entity.Student;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Teacher;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Topr
 */
@Singleton
public class TeachersSessionBean implements TeachersSessionBeanLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Teacher checkLogin(String login, String password){
        
        if(checkTeacher(login,password)){
            return em.find(Teacher.class, login);
        }else return null;
        
    }
    
    @Override
    public List<Sheduleitem> getSheduleItems(String login, String password) {
        
        if(checkUser(login, password)){
            return em.createNativeQuery("SELECT * FROM SheduleItem"
                    + " left join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup"
                    + " join schoolyear on schoolyear.idSchoolYear = studygroup.SchoolYear_idSchoolYear"
                    + " WHERE Users_Login = ?login AND schoolyear.isactualyear = true", Sheduleitem.class)
                    .setParameter("login", login)
                    .getResultList();
        }else {
            return null;
        }
    }
    
    @Override
    public List<Studygroup> getStudyGroups(String login, String password){
        //DISTINCT studygroup.idStudyGroup, studygroup.Name
        if(checkTeacher(login, password)){
            return em.createNativeQuery("SELECT DISTINCT studygroup.idStudyGroup, studygroup.Name FROM SheduleItem"
                    + " left join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup"
                    + " join schoolyear on schoolyear.idSchoolYear = studygroup.SchoolYear_idSchoolYear"
                    + " WHERE Users_Login = ?login AND schoolyear.isactualyear = true", Studygroup.class)
                    .setParameter("login", login)
                    .getResultList();
        }else {
            return null;
        }
    }
    @Override
    public List<Student> getStudents(String login, String password){
        if(checkTeacher(login, password)){
            return em.createNativeQuery("SELECT DISTINCT s.FirstName, s.MiddleName, s.LastName, s.Phone, s.Email, s.Login, s.BirthDate, s.StudyGroup_idStudyGroup FROM SheduleItem"
                    + " left join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup"
                    + " join schoolyear on schoolyear.idSchoolYear = studygroup.SchoolYear_idSchoolYear "
                    + " join Users s on studygroup.idStudyGroup = s.StudyGroup_idStudyGroup "
                    + " WHERE Users_Login = ?login AND schoolyear.isactualyear = true AND s.Role = 'S'", Student.class)
                    .setParameter("login", login)
                    .getResultList();
        }else {
            return null;
        }
    }
    @Override
    public List<Studysubject> getStudySubjects(){
        return  em.createNativeQuery("SELECT * FROM studysubject", Studysubject.class).getResultList();
    }
    
    private boolean checkTeacher(String login, String password){
        long tmp = (long)em.createNativeQuery("SELECT count(*) FROM Users u WHERE u.login = ?login AND u.password = ?password AND Role = 'T'")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
        if(tmp > 0){
            return true;
        }else return false;
    }
    private boolean checkStudent(String login, String password){
        long tmp = (long)em.createNativeQuery("SELECT count(*) FROM Users u WHERE u.login = ?login AND u.password = ?password AND Role = 'S'")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
        if(tmp > 0){
            return true;
        }else return false;
    }
    private boolean checkUser(String login, String password){
        long tmp = (long)em.createNativeQuery("SELECT count(*) FROM Users u WHERE u.login = ?login AND u.password = ?password")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
        if(tmp > 0){
            return true;
        }else return false;
    }
}