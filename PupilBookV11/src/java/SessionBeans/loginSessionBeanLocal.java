/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Users;
import javax.ejb.Local;

/**
 *
 * @author KastanNotas
 */
@Local
public interface loginSessionBeanLocal {
    public Users doLogin(Users u);
    public Users doLogin(String login, String password);
}
