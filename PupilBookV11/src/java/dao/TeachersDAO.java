/*
 * Copyright (C) 2015 Topr
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package dao;

import Entity.Results;
import Entity.Schoolyear;
import Entity.Studygroup;
import Entity.Teacher;
import Entity.Users;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;

/**
 *
 * @author Topr
 */
public class TeachersDAO {
    private final EntityManager em;

    public TeachersDAO(EntityManager em) {
        super();
        this.em = em;
    }
    
    public Teacher checkLogin(String login, String password){
        
        if(checkTeacher(login,password)){
            return em.find(Teacher.class, login);
        }else return null;
        
    }
    
    public List<Results> getResults(String login, String password){
        if(checkTeacher(login, password)){
            return em.createNativeQuery("select * from results WHERE Teacher_Login = ?login AND SchoolYear_idSchoolYear = ?syId", Results.class)
                    .setParameter("login", login)
                    .setParameter("syId", getActualSchoolYear())
                    .getResultList();
        }else {
            return null;
        }
    }
    
    public Collection<Teacher> getAllTeachers(){
        return em.createNamedQuery("Teachers.findAll").getResultList();
    }
    
    public Collection<Teacher> getTeachersByStudyGroup(Studygroup s) {
        return em.createNamedQuery("Teacher.byStudyGroupAndRole")
                .setParameter("studygroup", s)
                .getResultList();
    }
    
    public Teacher getTeacher(String UserId){
        return em.find(Teacher.class, UserId);
    }

    public Teacher saveTeacher(Teacher t){
        em.merge(t);
        em.flush();
        return t;
    }

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
