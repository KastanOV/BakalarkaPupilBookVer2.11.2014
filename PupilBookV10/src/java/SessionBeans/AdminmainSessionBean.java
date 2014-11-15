/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import Entity.Studygroup;
import Entity.Users;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
import java.util.Collection;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Singleton
public class AdminmainSessionBean implements AdminmainSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    private DAOFactory factory;
    
    private DAOFactory getFactory(){
        if(factory == null){
            factory = new DAOFactoryJPA(em);
        }
        return factory;
    }
//StudyGroups    
    @Override
    public Studygroup saveStudygroup(Studygroup s) {
        return getFactory().getStudygroupDAO().saveStudygroup(s);
    }
    @Override
    public List<Studygroup> getAllStudygroup() {
        return getFactory().getStudygroupDAO().getAllStudygroup();
    }
    @Override
    public Studygroup getStudygroup(int StudygroupId) {
        return getFactory().getStudygroupDAO().getStudygroup(StudygroupId);
    }
    @Override
    public void deleteStudygroup(Studygroup p) {
        getFactory().getStudygroupDAO().deleteStudygroup(p);
    }
    @Override
    public void deleteStudygroup(int StudygroupId) {
        getFactory().getStudygroupDAO().deleteStudygroup(StudygroupId);
    }
//Students and users
    @Override
    public Users createNewUser(Users s) {
        return getFactory().getStudentsDAO().createNewUser(s);
    }
    @Override
    public Collection<Users> getAllStudents() {
        return getFactory().getStudentsDAO().getAllStudents();
    }
    @Override
    public Users saveStudent(Users s) {
        return getFactory().getStudentsDAO().saveStudent(s);
    }
//Schoool Years    
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
}
