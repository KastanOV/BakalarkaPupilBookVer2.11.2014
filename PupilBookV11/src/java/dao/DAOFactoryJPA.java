/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;

/**
 *
 * @author KastanNotas
 */
public class DAOFactoryJPA implements DAOFactory{

    private final EntityManager em ;
    
    private ResultsDAO resultsDAO = null;
    private SchoolYearDAO schoolYearDAO = null;
    private SheduleItemsDAO sheduleItemsDAO = null;
    private StudentsDAO studentsDAO = null;
    private StudyGroupsDAO studyGroupDAO = null;
    private StudySubjectsDAO studySubjectsDAO = null;
    private TeachersDAO teachersDAO = null;
    private LoginDAO loginDAO = null;
    
    public DAOFactoryJPA(EntityManager em){
        super();
        this.em = em;
    }
    
    @Override
    public ResultsDAO getResultsDAO() {
        if(resultsDAO == null){
            resultsDAO = new ResultsDAO(em);
        }
        return resultsDAO;
    }

    @Override
    public SchoolYearDAO getSchoolYearDAO() {
        if(schoolYearDAO == null){
            schoolYearDAO = new SchoolYearDAO(em);
        }
        return schoolYearDAO;
    }

    @Override
    public SheduleItemsDAO getSheduleItemsDAO() {
        if(sheduleItemsDAO == null){
            sheduleItemsDAO = new SheduleItemsDAO(em);
        }
        return sheduleItemsDAO;
    }

    @Override
    public StudentsDAO getStudentsDAO() {
        if(studentsDAO == null){
            studentsDAO = new StudentsDAO(em);
        }
        return studentsDAO;
    }

    @Override
    public StudyGroupsDAO getStudyGroupDAO() {
        if(studyGroupDAO == null){
            studyGroupDAO = new StudyGroupsDAO(em);
        }
        return studyGroupDAO;
    }

    @Override
    public StudySubjectsDAO getStudySubjectsDAO() {
        if(studySubjectsDAO == null){
            studySubjectsDAO = new StudySubjectsDAO(em);
        }
        return studySubjectsDAO;
    }

    @Override
    public TeachersDAO getTeachersDAO() {
        if(teachersDAO == null){
            teachersDAO = new TeachersDAO(em);
        }
        return teachersDAO;
    }
    
    @Override
    public LoginDAO getLoginDAO(){
        if(loginDAO ==null){
            loginDAO = new LoginDAO(em);
        }
        return loginDAO;
    }

    @Override
    public void refreshEntityManager() {
        em.getEntityManagerFactory().getCache().evictAll();
    }
    
}
