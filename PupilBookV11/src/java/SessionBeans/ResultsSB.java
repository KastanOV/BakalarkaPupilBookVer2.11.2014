/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Results;
import Entity.ResultsExtended;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Teacher;
import dao.DAOFactory;
import dao.DAOFactoryJPA;
import java.math.BigDecimal;
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
    
    private DAOFactory factory;
    
    private DAOFactory getFactory(){
        if(factory == null){
            factory = new DAOFactoryJPA(em);
        }
        return factory;
    }
    
    @Override
    public servicesDTO.Results saveUploadedResult(servicesDTO.Results res) {
        return getFactory().getResultsDAO().saveUploadedResult(res);
    }
    
    @Override
    public List<Results> getStudentResults(String login, int StudySubjectID){
        return getFactory().getResultsDAO().getStudentResults(login, StudySubjectID);
    }
    
    @Override
    public void insertNewResult(Results res){
        getFactory().getResultsDAO().insertNewResult(res);
    }

    @Override
    public List<String> getAutoCompleteStrings(Studysubject sg, String input, Teacher t) {
        input = input + "%";
        int YearID = getFactory().getSchoolYearDAO().getActualSchoolYear().getIdSchoolYear();
        List<String> tmp = (List<String>) em.createNativeQuery("select distinct Description from results where StudySubject_idStudySubject = ?studySubject AND SchoolYear_idSchoolYear = ?SchoolYear and Description LIKE ?input AND Teacher_Login = ?teacher")
                .setParameter("studySubject", sg.getIdStudySubject())
                .setParameter("SchoolYear", YearID)
                .setParameter("input", input)
                .setParameter("teacher", t.getLogin())
                .getResultList();
        return tmp;
    }
    
    @Override
    public List<ResultsExtended> getStudentExtendResults(String TeacherLogin, String StudentLogin, int StudySubjectID){
        int YearID = getFactory().getSchoolYearDAO().getActualSchoolYear().getIdSchoolYear();
        List<String> tmp = (List<String>) em.createNativeQuery("select distinct Description "
                + " from results "
                + " where StudySubject_idStudySubject = ?StudySubject AND Teacher_Login = ?TeacherLogin AND SchoolYear_idSchoolYear = ?ActualYear ")
                .setParameter("StudySubject", StudySubjectID)
                .setParameter("TeacherLogin", TeacherLogin)
                .setParameter("ActualYear", YearID)
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

}
