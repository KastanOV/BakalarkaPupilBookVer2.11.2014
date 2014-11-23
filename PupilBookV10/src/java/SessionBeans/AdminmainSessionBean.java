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
import java.util.Collection;
import java.util.List;
import java.util.Random;
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
        if(s.getIdStudyGroup() != null){
            em.merge(s);
        } else {
            em.persist(s);
        }
        em.flush();
        return s;
    }
    @Override
    public List<Studygroup> getAllStudygroup() {
        return em.createNamedQuery("Studygroup.findAll").getResultList();
    }
    @Override
    public Studygroup getStudygroup(int StudygroupId) {
        return em.find(Studygroup.class, StudygroupId);
    }
    @Override
    public void deleteStudygroup(Studygroup p) {
        em.remove(em.find(Studygroup.class, p.getIdStudyGroup()));
    }
    @Override
    public void deleteStudygroup(int StudygroupId) {
        em.remove(em.find(Studygroup.class, StudygroupId));
    }
//Students and users
    @Override
    public Collection<Users> getByLastName(String lastName){
        lastName = lastName + "%";
        return em.createNamedQuery("Users.findByLastName")
                .setParameter("lastName", lastName)
                .getResultList();
    }
    @Override
    public Users createNewUser(Users s) {
        getFreeLogin(s);
        if(s.getPassword() == null) createPassword(s);
        em.persist(s);
        em.flush();
        return s;
    }
    
    @Override
    public Collection<Users> getAllStudents(){
        return em.createNamedQuery("Users.findByRole").setParameter("role", 'S').getResultList();
    }
    @Override
    public Collection<Users> getAllTeachers(){
        return em.createNamedQuery("Users.findByRole").setParameter("role", 'T').getResultList();
    }
    @Override
    public Users saveUser(Users s){
        em.merge(s);
        em.flush();
        return s;
    }

    @Override
    public Collection<Users> getStudentByStudyGroup(Studygroup s) {
        return em.createNamedQuery("Users.byStudyGroupAndRole")
                .setParameter("role", 'S')
                .setParameter("studygroup", s)
                .getResultList();
    }

    @Override
    public Collection<Users> getTeachersByStudyGroup(Studygroup s) {
        return em.createNamedQuery("Users.byStudyGroupAndRole")
                .setParameter("role", 'T')
                .setParameter("studygroup", s)
                .getResultList();
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
        if(s.getIdSchoolYear() != null){
            em.merge(s);
        } else {
            em.persist(s);
        }
        em.flush();
        return s;
    }
    @Override
    public List<Schoolyear> getAllSchoolYears() {
        return em.createNamedQuery("Schoolyear.findAll").getResultList();
    }
    @Override
    public Schoolyear getSchoolyear(int id) {
        return em.find(Schoolyear.class, id);
    }
    @Override
    public void deleteSchooYear(Schoolyear s) {
        em.remove(em.find(Schoolyear.class, s.getIdSchoolYear()));
    }
    @Override
    public void deleteSchooYear(int id) {
        em.remove(em.find(Schoolyear.class, id));
    }

    @Override
    public List<Studygroup> getEditedStudyGroup(Schoolyear s){
        return em.createNamedQuery("Studygroup.findBySchoolyear")
                .setParameter("SchoolYearID", s)
                .getResultList();
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
        s.setLogin(LoginPrefix + getPostFix(em.createNamedQuery("Users.loginCounter")
                .setParameter("createLogin", LoginPrefix + "%")
                .getSingleResult()
                .toString()));
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
