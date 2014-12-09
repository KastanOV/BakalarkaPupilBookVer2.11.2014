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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Topr
 */
@Entity
@XmlRootElement
@Table(name = "Users")
@DiscriminatorValue(value = "S")
@NamedQueries({
    //TODO je treba zaridit generovani novych useru !! stara verze napojena na tabulku Users
    @NamedQuery(name = "Student.findAll", query = "SELECT u FROM Student u"),
    @NamedQuery(name = "Student.byStudyGroupAndRole", query = "SELECT u FROM Student u WHERE u.studyGroupidStudyGroup = :studygroup" ),
    @NamedQuery(name = "Student.findByLastName", query = "SELECT u FROM Student u WHERE u.lastName LIKE :lastName"),
    @NamedQuery(name = "Student.doLogin", query = "SELECT u FROM Student u WHERE u.login = :login and u.password = :password")})
public class Student extends Users{
    
}
