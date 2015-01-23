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

import Entity.Parent;
import Entity.Parrentstudent;
import Entity.Student;
import Entity.Studygroup;
import Entity.Users;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;

/**
 *
 * @author Topr
 */
public class StudentsDAO {
    
    private final EntityManager em;
    
    public StudentsDAO(EntityManager em){
        super();
        this.em = em;
    }
    
    public Collection<Student> getByLastName(String lastName){
        lastName = lastName + "%";
        return em.createNamedQuery("Student.findByLastName")
                .setParameter("lastName", lastName)
                .getResultList();
    }
    public Collection<Student> getByParameters(String lastName, Date start, Date end, boolean deleted){
        lastName = lastName + "%";
        return em.createNativeQuery("SELECT * FROM Users u WHERE u.lastName LIKE ?lastName AND u.birthDate BETWEEN ?start AND ?end AND u.Role = ?s deleted = ?del", Student.class)
                .setParameter("lastName", lastName)
                .setParameter("start", start)
                .setParameter("end", end)
                .setParameter("s", 'S')
                .setParameter("deleted", deleted)
                .getResultList();
    }

    public Student createNewUser(Student s) {
        getFreeLogin(s);
        Parent par = createNewParent(s);
        Parrentstudent ps = new Parrentstudent();
        ps.setStudentLogin(s);
        ps.setParentLogin(par);
        if(s.getPassword() == null) createPassword(s);
        em.persist(par);
        em.persist(s);
        em.persist(ps);
        em.flush();
        return s;
    }
    
    public Collection<Student> getAllStudents(){
        return em.createNamedQuery("Student.findAll").getResultList();
    }

    public Student saveUser(Student s){
        em.merge(s);
        em.flush();
        return s;
    }

    public Collection<Student> getStudentByStudyGroup(Studygroup s) {
        return em.createNamedQuery("Student.byStudyGroupAndRole")
                .setParameter("studygroup", s)
                .getResultList();
    }

    public Student getStudent(String UserId){
        return em.find(Student.class, UserId);
    }
    
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
    
}
