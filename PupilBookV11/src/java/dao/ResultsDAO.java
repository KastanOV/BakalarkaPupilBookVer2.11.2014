/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Results;
import Entity.Schoolyear;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Topr
 */
public class ResultsDAO {
    private final EntityManager em;
    
    public ResultsDAO(EntityManager em){
        super();
        this.em = em;
    }
    
    public servicesDTO.Results saveUploadedResult(servicesDTO.Results res) {
        Date date = new Date(Long.parseLong(res.getDate()));
        int tmp = em.createNativeQuery("INSERT INTO results ( Description, Score, Date, Teacher_Login, Student_Login, StudySubject_idStudySubject, SchoolYear_idSchoolYear)"
                + " VALUES (?desc, ?score, ?date, ?tl, ?sl, ?ssId, ?syId)")
                .setParameter("desc", res.getDesc())
                .setParameter("score", res.getScore())
                .setParameter("date", date)
                .setParameter("tl", res.gettL())
                .setParameter("sl", res.getsL())
                .setParameter("ssId", res.getSsId())
                .setParameter("syId", getActualSchoolYear())
                .executeUpdate();
        int id = (int) em.createNativeQuery("select max(idResults) FROM Results").getSingleResult();
        res.setId(id);
        return res;
    }
    
   
    public List<Results> getStudentResults(String login, int StudySubjectID){
        return em.createNativeQuery("select * from results WHERE Student_Login = ?login AND SchoolYear_idSchoolYear = ?syId AND StudySubject_idStudySubject = ?StudySubjectID", Results.class)
                    .setParameter("login", login)
                    .setParameter("syId", getActualSchoolYear())
                    .setParameter("StudySubjectID", StudySubjectID)
                    .getResultList();
    }
    
    
    public void insertNewResult(Results res){
        res.setDate(new java.util.Date());
        
        int tmp = em.createNativeQuery("INSERT INTO results ( Description, Score, Date, Teacher_Login, Student_Login, StudySubject_idStudySubject, SchoolYear_idSchoolYear)"
                + " VALUES (?desc, ?score, ?date, ?tl, ?sl, ?ssId, ?syId)")
                .setParameter("desc", res.getDescription())
                .setParameter("score", res.getScore())
                .setParameter("date", res.getDate())
                .setParameter("tl", res.getTeacherLogin().getLogin())
                .setParameter("sl", res.getStudentLogin().getLogin())
                .setParameter("ssId", res.getStudySubjectidStudySubject().getIdStudySubject())
                .setParameter("syId", getActualSchoolYear())
                .executeUpdate();
    }
    
    private int getActualSchoolYear(){
        Schoolyear idActualYear = (Schoolyear) em.createNativeQuery("SELECT * FROM schoolyear WHERE schoolyear.isactualyear = true", Schoolyear.class)
                .getSingleResult();
        return idActualYear.getIdSchoolYear();
    }
    
}
