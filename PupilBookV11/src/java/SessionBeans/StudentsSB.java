/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Student;
import Entity.Studygroup;
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
public class StudentsSB implements StudentsSBLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Collection<Student> getByLastName(String lastName){
        lastName = lastName + "%";
        return em.createNamedQuery("Student.findByLastName")
                .setParameter("lastName", lastName)
                .getResultList();
    }
    @Override
    public Student createNewUser(Student s) {
        getFreeLogin(s);
        if(s.getPassword() == null) createPassword(s);
        em.persist(s);
        em.flush();
        return s;
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
