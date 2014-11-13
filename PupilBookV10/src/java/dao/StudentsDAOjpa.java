/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Users;
import java.util.Random;
import javax.persistence.EntityManager;

/**
 *
 * @author KastanNotas
 */
public class StudentsDAOjpa {
    private final EntityManager em;
    
    public StudentsDAOjpa(EntityManager em){
        super();
        this.em = em;
    }
    
    public Users saveStudent(Users s){
        if(s.getLogin() != null){
            em.merge(s);
        } else {
            //TODO generovani loginu
            em.persist(s);
        }
        em.flush();
        return s;
    }
    public Users createNewUser(Users s){
        getFreeLogin(s);
        createPassword(s);
        return s;
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
        String LoginPrefix = s.getLastName().substring(0,3);
        s.setLogin(getPostFix(em.createNamedQuery("Users.loginCounter").setParameter("createLogin", LoginPrefix).getSingleResult().toString()));
    }
    private String getPostFix(String Postfix){
        switch(Postfix.length()){
            case 1 : return "00" + Postfix;
            case 2 : return "0" + Postfix;
            default : return Postfix;    
        }
    };
}
