/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Studygroup;
import Entity.Teacher;
import Entity.Users;
import java.util.Collection;
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
}
