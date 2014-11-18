/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Schoolyear;
import Entity.Sheduleitem;
import Entity.Studygroup;
import Entity.Studysubject;
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
//Shedule Items
    @Override
    public void insertNewSheduleItem(Sheduleitem s) {
        em.persist(s);
    }

    @Override
    public Sheduleitem saveSheduleItem(Sheduleitem s) {
        em.merge(s);
        return s;
    }
//Subjects
    @Override
    public Collection<Studysubject> getAllStudySubjects(){
        return em.createNamedQuery("Studysubject.findAll").getResultList();
    }
    @Override
    public Studysubject insertNewStudySubject(Studysubject s){
        em.persist(s);
        em.flush();
        return s;
    }
    @Override
    public Studysubject saveStudySubject(Studysubject s){
        em.merge(s);
        em.flush();
        return s;
    }
    @Override
    public Studysubject getStudysubject(int id) {
        return em.find(Studysubject.class, id);
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
        return getFactory().getUsersDAO().createNewUser(s);
    }
    @Override
    public Collection<Users> getAllStudents() {
        return getFactory().getUsersDAO().getAllStudents();
    }
    @Override
    public Users saveUser(Users s) {
        return getFactory().getUsersDAO().saveUser(s);
    }
    @Override
    public Collection<Users> getAllTeachers() {
        return getFactory().getUsersDAO().getAllTeachers();
    }

    /**
     *
     * @param UserId
     * @return
     */
    @Override
    public Users getUser(String UserId){
        return em.find(Users.class, UserId);
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

    @Override
    public List<Studygroup> getEditedStudyGroup(Schoolyear s) {
        return getFactory().getStudygroupDAO().getEditedStudyGroup(s);
    }

    

    

    
}
