    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Admin;
import Entity.Parent;
import Entity.Student;
import Entity.Studygroup;
import Entity.Teacher;
import Entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KastanNotas
 */
@Stateless (mappedName="loginSessionBean")
public class loginSessionBean implements loginSessionBeanLocal {
    @PersistenceContext
    private EntityManager em;
        
    @Override
    public Users doLogin(Users u){
        try{
            Student loaded = (Student) em.createNamedQuery("Student.doLogin")
                .setParameter("login", u.getLogin())
                .setParameter("password", u.getPassword())
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            
        }
        
        try{
            Parent loaded = (Parent) em.createNamedQuery("Parent.doLogin")
                .setParameter("login", u.getLogin())
                .setParameter("password", u.getPassword())
                .getSingleResult();
            
            return loaded;
        } catch (Exception e){
            
        }
        
        try{
            Admin loaded = (Admin) em.createNamedQuery("Admin.doLogin")
                .setParameter("login", u.getLogin())
                .setParameter("password", u.getPassword())
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            
        }
        try{
            Teacher loaded = (Teacher) em.createNamedQuery("Teacher.doLogin")
                .setParameter("login", u.getLogin())
                .setParameter("password", u.getPassword())
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            return u;
        }
    }

    @Override
    public Users doLogin(String login, String password) {
        try{
            Student loaded = (Student) em.createNamedQuery("Student.doLogin")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            
        }
        
        try{
            Parent loaded = (Parent) em.createNamedQuery("Parent.doLogin")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
            Integer StudyGroup = (Integer) em.createNativeQuery("select StudyGroup_idStudyGroup from parrentstudent " +
                        " join users on Student_Login = users.Login " +
                        " where Parent_Login = ?login")
                    .setParameter("login", loaded.getLogin())
                    .getSingleResult();
            Studygroup sgtmp = em.find(Studygroup.class, StudyGroup);
            loaded.setStudyGroupidStudyGroup(sgtmp);
            return loaded;
        } catch (Exception e){
            
        }
        
        try{
            Admin loaded = (Admin) em.createNamedQuery("Admin.doLogin")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            
        }
        try{
            Teacher loaded = (Teacher) em.createNamedQuery("Teacher.doLogin")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
            return loaded;
        } catch (Exception e){
            return null;
        }
    }
}
