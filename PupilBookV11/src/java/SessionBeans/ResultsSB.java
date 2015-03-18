/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Results;
import Entity.ResultsExtended;
import Entity.Schoolyear;
import Entity.Studysubject;
import Entity.Teacher;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Topr
 */
@Stateless
public class ResultsSB implements ResultsSBLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
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
    
    @Override
    public List<Results> getStudentResults(String TeacherLogin, String login, int StudySubjectID){
        int YearID = getActualSchoolYear();
        return em.createNativeQuery("select * from results WHERE Student_Login = ?login AND SchoolYear_idSchoolYear = ?syId AND StudySubject_idStudySubject = ?StudySubjectID  AND Teacher_Login = ?TeacherLogin", Results.class)
                    .setParameter("login", login)
                    .setParameter("syId", getActualSchoolYear())
                    .setParameter("StudySubjectID", StudySubjectID)
                    .setParameter("TeacherLogin", TeacherLogin)
                    .getResultList();
    }
    
    @Override
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

    @Override
    public List<String> getAutoCompleteStrings(Studysubject sg, String input, Teacher t) {
        input = input + "%";
        int YearID = getActualSchoolYear();
        List<String> tmp = (List<String>) em.createNativeQuery("select distinct Description from results where StudySubject_idStudySubject = ?studySubject AND SchoolYear_idSchoolYear = ?SchoolYear and Description LIKE ?input AND Teacher_Login = ?teacher")
                .setParameter("studySubject", sg.getIdStudySubject())
                .setParameter("SchoolYear", YearID)
                .setParameter("input", input)
                .setParameter("teacher", t.getLogin())
                .getResultList();
        return tmp;
    }
    
    @Override
    public List<ResultsExtended> getStudentExtendResults(String TeacherLogin, String StudentLogin, int StudySubjectID, int editedStudyGroup){
        int YearID = getActualSchoolYear();
        List<String> tmp = (List<String>) em.createNativeQuery("select distinct Description "
                + " from results join users on results.Student_Login = users.Login "
                + " where StudySubject_idStudySubject = ?StudySubject AND Teacher_Login = ?TeacherLogin AND SchoolYear_idSchoolYear = ?ActualYear AND users.StudyGroup_idStudyGroup = ?sgID")
                .setParameter("StudySubject", StudySubjectID)
                .setParameter("TeacherLogin", TeacherLogin)
                .setParameter("ActualYear", YearID)
                .setParameter("sgID", editedStudyGroup)
                .getResultList();
        List<ResultsExtended> exRes = new ArrayList<>();
        for (String item : tmp){
            BigDecimal avg = (BigDecimal) em.createNativeQuery("select AVG(Score) from results " +
                    "where StudySubject_idStudySubject = ?StudySubject AND Teacher_Login = ?TeacherLogin AND SchoolYear_idSchoolYear = ?ActualYear and Description = ?desc")
                .setParameter("StudySubject", StudySubjectID)
                .setParameter("TeacherLogin", TeacherLogin)
                .setParameter("ActualYear", YearID)
                .setParameter("desc", item)
                .getSingleResult();
            List<Results> tmpResults = (List<Results>) em.createNativeQuery("select * from results " +
                    "where StudySubject_idStudySubject = ?StudySubject AND Teacher_Login = ?TeacherLogin AND Student_Login = ?student AND SchoolYear_idSchoolYear = ?ActualYear and Description = ?desc", Results.class)
                .setParameter("StudySubject", StudySubjectID)
                .setParameter("TeacherLogin", TeacherLogin)
                .setParameter("ActualYear", YearID)
                .setParameter("student", StudentLogin)
                .setParameter("desc", item)
                .getResultList();
            if(tmpResults.isEmpty()){
                ResultsExtended extResTmp = new ResultsExtended();
                extResTmp.setPrumer(avg.doubleValue());
                extResTmp.setDescription(item);
                extResTmp.setScore((short)-1);
                exRes.add(extResTmp);
            } else {
                for(Results resItems : tmpResults){
                    ResultsExtended extResTmp = new ResultsExtended();
                    extResTmp.setDate(resItems.getDate());
                    extResTmp.setDescription(resItems.getDescription());
                    extResTmp.setIdResults(resItems.getIdResults());
                    extResTmp.setPrumer(avg.doubleValue());
                    extResTmp.setSchoolYearidSchoolYear(resItems.getSchoolYearidSchoolYear());
                    extResTmp.setScore(resItems.getScore());
                    extResTmp.setStudentLogin(resItems.getStudentLogin());
                    extResTmp.setStudySubjectidStudySubject(resItems.getStudySubjectidStudySubject());
                    extResTmp.setTeacherLogin(resItems.getTeacherLogin());
                    exRes.add(extResTmp);
                }    
            }
        }
        return exRes;
    }

    @Override
    public void saveResult(Results res) {
        short scTmp = res.getScore();
        String descTmp = res.getDescription();
        res = em.find(Results.class, res.getIdResults());
        res.setScore(scTmp);
        res.setDescription(descTmp);
        em.merge(res);
        em.flush();
    }
    @Override
    public void deleteResult(Results res) {
        res = em.find(Results.class, res.getIdResults());
        em.remove(res);
        em.flush();
    }
    
    private int getActualSchoolYear(){
        Schoolyear idActualYear = (Schoolyear) em.createNativeQuery("SELECT * FROM schoolyear WHERE schoolyear.isactualyear = true", Schoolyear.class)
                .getSingleResult();
        return idActualYear.getIdSchoolYear();
    }
    
}
