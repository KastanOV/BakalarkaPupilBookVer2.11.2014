/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Student;
import Entity.Studygroup;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
@Stateless
public class StudentsSB implements StudentsSBLocal {
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
    public Collection<Student> getByLastName(String lastName){
        return getFactory().getStudentsDAO().getByLastName(lastName);
    }
    @Override
    public Student createNewUser(Student s) {
        return getFactory().getStudentsDAO().createNewUser(s);
    }
    
    @Override
    public Collection<Student> getAllStudents(){
        return getFactory().getStudentsDAO().getAllStudents();
    }
    @Override
    public Student saveUser(Student s){
        return getFactory().getStudentsDAO().saveUser(s);
    }

    @Override
    public Collection<Student> getStudentByStudyGroup(Studygroup s) {
        return getFactory().getStudentsDAO().getStudentByStudyGroup(s);
    }
    @Override
    public Student getStudent(String UserId){
        return getFactory().getStudentsDAO().getStudent(UserId);
    }
    
    @Override
    public List<Student> getStudents(String login, String password){
        return getFactory().getStudentsDAO().getStudents(login, password);
    }
    @Override
    public List<Student> getStudents(String login, String password, int StudyGroupId){
        return getFactory().getStudentsDAO().getStudents(login, password, StudyGroupId);
    }
}
