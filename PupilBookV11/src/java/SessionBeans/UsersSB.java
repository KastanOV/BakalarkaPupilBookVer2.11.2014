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
package SessionBeans;

import Entity.Parent;
import Entity.Parrentstudent;
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
public class UsersSB implements UsersSBLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Collection<Users> getUsers(Boolean teachers, Boolean isDeleted, String LastName, Studygroup sg){
        String role = teachers ? "T" : "S";
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Users u WHERE u.Role = ?s ");
        if(!LastName.equals("")){
            LastName = LastName + "%";
            query.append(" AND u.lastName LIKE ?lastName ");
        }
        if(isDeleted == null){
            isDeleted = false;
        }
        query.append(" AND deleted = ?del ");
        int sgtmp = 0;
        if(sg != null){
            query.append(" AND StudyGroup_idStudyGroup = ?sg");
            sgtmp = sg.getIdStudyGroup();
        }
        
        Collection<Users> listTmp = em.createNativeQuery(query.toString(), Users.class)
                .setParameter("lastName", LastName)
                .setParameter("s", role)
                .setParameter("del", isDeleted)
                .setParameter("sg", sgtmp)
                .getResultList();
        return listTmp;
    }
    @Override
    public void deleteUser(Users u){
        try{
            
            em.remove(em.find(Users.class, u.getLogin()));
        } catch (Exception e){
            u.setDeleted(true);
            em.merge(em.find(Users.class, u.getLogin()));
        }
        em.flush();
    }

    @Override
    public Users findParrent(Users u) {
        String parrentID = "";
        try {
        parrentID = (String) em.createNativeQuery("select up.login from users us " +
                    " join parrentstudent on parrentstudent.Student_Login = us.Login " +
                    " join users up on parrentstudent.Parent_Login = up.Login " +
                    " where us.login = ?login")
                .setParameter("login", u.getLogin())
                .getSingleResult();
        } catch (Exception e) {
            Student tmp = em.find(Student.class, u.getLogin());
            Parent par = createNewParent(tmp);
            Parrentstudent ps = new Parrentstudent();
            ps.setStudentLogin(tmp);
            ps.setParentLogin(par);
            if(tmp.getPassword() == null) createPassword(tmp);
            tmp.setDeleted(false);
            par.setDeleted(false);
            em.persist(par);
            em.persist(ps);
            em.flush();
            return par;
        }
        return em.find(Users.class, parrentID);
    }

    @Override
    public void saveUser(Users u) {
        em.merge(u);
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
}
