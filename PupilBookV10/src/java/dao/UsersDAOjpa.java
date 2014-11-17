/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Users;
import java.util.Collection;
import java.util.Random;
import javax.persistence.EntityManager;

/**
 *
 * @author KastanNotas
 */
public class UsersDAOjpa {
    private final EntityManager em;
    
    public UsersDAOjpa(EntityManager em){
        super();
        this.em = em;
    }
    public Collection<Users> getAllStudents(){
        return em.createNamedQuery("Users.findByRole").setParameter("role", 'S').getResultList();
    }
    public Collection<Users> getAllTeachers(){
        return em.createNamedQuery("Users.findByRole").setParameter("role", 'T').getResultList();
    }
    public Users saveUser(Users s){
        em.merge(s);
        em.flush();
        return s;
    }
    
    public Users createNewUser(Users s){
        getFreeLogin(s);
        if(s.getPassword() == null) createPassword(s);
        em.persist(s);
        em.flush();
        
        return s;
    }
    public Users doLogin(Users u){
        try{
            Users loaded = (Users) em.createNamedQuery("Users.doLogin")
                .setParameter("login", u.getLogin())
                .setParameter("password", u.getPassword())
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            return u;
        }
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
