/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Results;
import Entity.Schoolyear;
import Entity.Studygroup;
import Entity.Teacher;
import Entity.Users;
import java.util.Collection;
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
public class TeachersSB implements TeachersSBLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Teacher checkLogin(String login, String password){
        if(checkTeacher(login,password)){
            return em.find(Teacher.class, login);
        }else return null;
        
        
    }
    
    @Override
    public List<Results> getResultsTeacher(String login, String password){
        if(checkTeacher(login, password)){
            return em.createNativeQuery("select * from results WHERE Teacher_Login = ?login AND SchoolYear_idSchoolYear = ?syId", Results.class)
                    .setParameter("login", login)
                    .setParameter("syId", getActualSchoolYear())
                    .getResultList();
        }else {
            return null;
        }
    }
    
    
    
    @Override
    public Collection<Teacher> getAllTeachers(){
        return em.createNamedQuery("Teachers.findAll").getResultList();
    }
    
    @Override
    public Collection<Teacher> getTeachersByStudyGroup(Studygroup s) {
        return em.createNamedQuery("Teacher.byStudyGroupAndRole")
                .setParameter("studygroup", s)
                .getResultList();
    }
    
    @Override
    public Teacher getTeacher(String UserId){
        return em.find(Teacher.class, UserId);
    }
    @Override
    public Teacher saveTeacher(Teacher t){
        em.merge(t);
        em.flush();
        return t;
    }
    @Override
    public Teacher createNewTeacher(Teacher t){
        getFreeLogin(t);
        if(t.getPassword() == null) createPassword(t);
        em.persist(t);
        em.flush();
        return t;
    }
    
    @Override
    public Collection<Teacher> getTeachersByAtributes(Boolean isDeleted, String lastName) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Users u WHERE u.Role = ?s ");
        if(!lastName.equals("")){
            lastName = lastName + "%";
            query.append(" AND u.lastName LIKE ?lastName ");
        }
        
        if(isDeleted == null){
            isDeleted = false;
        }
        query.append(" AND deleted = ?del ");
        
        List<Teacher> listTmp = em.createNativeQuery(query.toString(), Teacher.class)
                .setParameter("lastName", lastName)
                .setParameter("s", 'T')
                .setParameter("del", isDeleted)
                .getResultList();
        return listTmp;
    }
    
    @Override
    public Collection<Teacher> getTeachersForShedule(short day, short hour) {
        return null;
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
    private int getActualSchoolYear(){
        Schoolyear idActualYear = (Schoolyear) em.createNativeQuery("SELECT * FROM schoolyear WHERE schoolyear.isactualyear = true", Schoolyear.class)
                .getSingleResult();
        return idActualYear.getIdSchoolYear();
    }

    

    
}
