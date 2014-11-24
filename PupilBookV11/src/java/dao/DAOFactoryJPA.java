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

    private final EntityManager em;
    
    private SchooyearDAOjpa schoolyearJpa = null;
    private StudygroupDAOjpa studygroupJpa = null;
    private UsersDAOjpa studentsJpa = null;
    
    public DAOFactoryJPA(EntityManager em){
        super();
        this.em = em;
    }
    
    /**
     *
     * @return
     */
    
    @Override
    public SchooyearDAOjpa getSchoolYearDAO() {
        if(schoolyearJpa == null){
            schoolyearJpa = new SchooyearDAOjpa(em);
        }
        return schoolyearJpa;
    }

    /**
     *
     * @return
     */
    @Override
    public StudygroupDAOjpa getStudygroupDAO() {
        if(studygroupJpa == null){
            studygroupJpa = new StudygroupDAOjpa(em);
        }
        return studygroupJpa;
    }

    /**
     *
     * @return
     */
    @Override
    public UsersDAOjpa getUsersDAO() {
        if(studentsJpa == null){
            studentsJpa = new UsersDAOjpa(em);
        }
        return studentsJpa;
    }
    
}
