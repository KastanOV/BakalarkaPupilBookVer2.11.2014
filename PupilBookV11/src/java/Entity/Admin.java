/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "Users")
@DiscriminatorValue(value = "A")
@NamedQueries({
    @NamedQuery(name = "Admin.doLogin", query = "SELECT u FROM Admin u WHERE u.login = :login and u.password = :password")})
public class Admin extends Users {
    
    
}
