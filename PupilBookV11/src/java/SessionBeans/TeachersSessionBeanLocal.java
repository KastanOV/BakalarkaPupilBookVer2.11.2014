/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Teacher;
import javax.ejb.Local;

/**
 *
 * @author Topr
 */
@Local
public interface TeachersSessionBeanLocal {
   public Teacher checkLogin(String login, String password); 
}
