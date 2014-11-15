/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Users;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author KastanNotas
 */
@Local
public interface StudentsSBLocal {
    public Users createNewUser(Users s);
    public Users saveStudent(Users s);
    public Collection<Users> getAllStudents();
}
