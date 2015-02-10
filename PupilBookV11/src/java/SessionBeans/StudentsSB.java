/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Parent;
import Entity.Parrentstudent;
import Entity.Results;
import Entity.Schoolyear;
import Entity.Student;
import Entity.Studygroup;
import Entity.Studysubject;
import Entity.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
    
    @Override
    public List<servicesDTO.StudySubject> getStudySubjects(String login){
        List<Studysubject> tmp = em.createNativeQuery(
                    "select Distinct studysubject.Name, studysubject.idStudySubject, studysubject.ShortName from results " +
                    "LEFT JOIN studysubject on studysubject.idStudySubject = results.StudySubject_idStudySubject " +
                    "WHERE Student_Login = ?login AND SchoolYear_idSchoolYear = ?syId " +
                    "order by Date Desc", Studysubject.class)
                    .setParameter("login", login)
                    .setParameter("syId", getActualSchoolYear())
                    .getResultList();
        
        List<servicesDTO.StudySubject> ret = new ArrayList<>();
        for(Studysubject item : tmp){
                servicesDTO.StudySubject StudySubjectNew = new servicesDTO.StudySubject(item.getIdStudySubject(), item.getName(), item.getShortName());
                ret.add(StudySubjectNew);
            }
        return ret;
    }
    
    @Override
    public List<servicesDTO.Results> getResultsByStudent(String login, String password){
        if(checkStudent(login, password)){
            List<Results> tmp = em.createNativeQuery("select * from results WHERE Student_Login = ?login AND SchoolYear_idSchoolYear = ?syId order by Date Desc", Results.class)
                .setParameter("login", login)
                .setParameter("syId", getActualSchoolYear())
                .getResultList();
        List<servicesDTO.Results> resultsEntity = new ArrayList<>();
        for(Results item : tmp){
            int inttmp = (int) (item.getScore());
            servicesDTO.Results tmpr = new servicesDTO.Results(item.getIdResults(), item.getDescription(), inttmp, item.getDate().toString(), item.getStudySubjectidStudySubject().getIdStudySubject(), null, null, null);
            resultsEntity.add(tmpr);
        }
        return resultsEntity;
        } else return null;
    }
    @Override
    public List<servicesDTO.Results> getResultsByStudentStudySubject(String login, String password, int StudySubject){
        if(checkStudent(login, password)){
            List<Results> tmp = em.createNativeQuery("select * from results WHERE Student_Login = ?login AND SchoolYear_idSchoolYear = ?syId and StudySubject_idStudySubject = ?ss order by Date Desc", Results.class)
                .setParameter("login", login)
                .setParameter("syId", getActualSchoolYear())
                .setParameter("ss", StudySubject)
                .getResultList();
        List<servicesDTO.Results> resultsEntity = new ArrayList<>();
        for(Results item : tmp){
            int inttmp = (int) (item.getScore());
            servicesDTO.Results tmpr = new servicesDTO.Results(item.getIdResults(), item.getDescription(), inttmp, item.getDate().toString(), item.getStudySubjectidStudySubject().getIdStudySubject(), null, null, null);
            resultsEntity.add(tmpr);
        }
        return resultsEntity;
        } else return null;
    }
    
    @Override
    public Student createNewUser(Student s) {
        getFreeLogin(s);
        Parent par = createNewParent(s);
        Parrentstudent ps = new Parrentstudent();
        ps.setStudentLogin(s);
        ps.setParentLogin(par);
        if(s.getPassword() == null) createPassword(s);
        s.setDeleted(false);
        par.setDeleted(false);
        em.persist(par);
        em.persist(s);
        em.persist(ps);
        em.flush();
        return s;
    }
    
    @Override
    public Student studentLogin(String login, String password){
        if(checkStudent(login,password)){
            return em.find(Student.class, login);
        }else return null;
        
        
    }
    
    @Override
    public Collection<Student> getAllStudents(){
        return em.createNamedQuery("Student.findAll").getResultList();
    }
    @Override
    public Student saveUser(Student s){
        em.merge(s);
        em.flush();
        return s;
    }

    @Override
    public Collection<Student> getStudentByStudyGroup(Studygroup s) {
        return em.createNamedQuery("Student.byStudyGroupAndRole")
                .setParameter("studygroup", s)
                .getResultList();
    }
    @Override
    public Student getStudent(String UserId){
        return em.find(Student.class, UserId);
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
    public List<Student> getStudents(String login, String password, int StudyGroupId){
        if(checkTeacher(login, password)){
            return em.createNativeQuery("SELECT DISTINCT s.FirstName, s.MiddleName, s.LastName, s.Phone, s.Email, s.Login, s.BirthDate, s.StudyGroup_idStudyGroup FROM SheduleItem"
                    + " left join studygroup on sheduleitem.StudyGroup_idStudyGroup = studygroup.idStudyGroup"
                    + " join schoolyear on schoolyear.idSchoolYear = studygroup.SchoolYear_idSchoolYear "
                    + " join Users s on studygroup.idStudyGroup = s.StudyGroup_idStudyGroup "
                    + " WHERE Users_Login = ?login AND schoolyear.isactualyear = true AND s.Role = 'S' AND studygroup.idStudyGroup = ?StudyGroupID", Student.class)
                    .setParameter("login", login)
                    .setParameter("StudyGroupID", StudyGroupId)
                    .getResultList();
        }else {
            return null;
        }
    }

    @Override
    public Collection<Student> getByParameters(String lastName, Date start, Date end, Boolean deleted) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Users u WHERE u.Role = ?s ");
        if(!lastName.equals("")){
            lastName = lastName + "%";
            query.append(" AND u.lastName LIKE ?lastName ");
        }
        if(start != null && end != null){
            query.append(" AND u.birthDate BETWEEN ?start AND ?end ");
        }
        if(deleted == null){
            deleted = false;
        }
        query.append(" AND deleted = ?del ");
        
        List<Student> listTmp = em.createNativeQuery(query.toString(), Student.class)
                .setParameter("lastName", lastName)
                .setParameter("start", start)
                .setParameter("end", end)
                .setParameter("s", 'S')
                .setParameter("del", deleted)
                .getResultList();
        return listTmp;
    }
    
    private Parent createNewParent(Student s){
        Parent p = new Parent();
        p.setLogin("p" + s.getLogin());
        p.setBirthDate(s.getBirthDate());
        p.setEmail(s.getEmail());
        p.setFirstName(s.getFirstName());
        p.setLastName(s.getLastName());
        p.setMiddleName(s.getMiddleName());
        createPassword(p);
        p.setPhone(s.getPhone());
        return p;
    }
    private void createPassword(Users s){
        char[] symbols;
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        for (char ch = 'A'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
        Random random = new Random();
        
        StringBuilder value = new StringBuilder();

        for (int idx = 0; idx < 10; ++idx) 
            value.append(symbols[random.nextInt(symbols.length)]);
        s.setPassword(value.toString());
    }
    private void getFreeLogin(Users s){
        String LoginPrefix = removeDiak(s.getLastName()
                .substring(0,3)
                .toUpperCase()
                .trim());
        Long numberOfLogin = (Long) em.createNativeQuery("SELECT COUNT(*) FROM Users WHERE login LIKE ?createLogin")
                .setParameter("createLogin", LoginPrefix + "%")
                .getSingleResult();
        s.setLogin(LoginPrefix + getPostFix(String.valueOf(numberOfLogin)));
    }
    private String getPostFix(String Postfix){
        switch(Postfix.length()){
            case 1 : return "00" + Postfix;
            case 2 : return "0" + Postfix;
            default : return Postfix;    
        }
    };
    private String removeDiak(String retazec){
       String retazecBD="";
       String sdiak="áäčďěéíĺžňóöôŕřšťúüýžźÁÄČĎĚÉÍĹŇÓÖÔŔŘŤÚÜÝŠŽŐőÖöŰűÜü";
       String bdiak="aacdeeillnooorrstuuyzzAACDEEILNOOORRTUUYSZOoOoUuUu";
       for (int l=0;l<retazec.length();l++){
           if (sdiak.indexOf(retazec.charAt(l))!=-1)
               retazecBD+=bdiak.charAt(sdiak.indexOf(retazec.charAt(l)));
           else
               retazecBD+=retazec.charAt(l);
       }
       return retazecBD;
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
    
    private int getActualSchoolYear(){
        Schoolyear idActualYear = (Schoolyear) em.createNativeQuery("SELECT * FROM schoolyear WHERE schoolyear.isactualyear = true", Schoolyear.class)
                .getSingleResult();
        return idActualYear.getIdSchoolYear();
    }

    
}
