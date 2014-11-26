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
@DiscriminatorValue(value = "T")
@NamedQueries({
    @NamedQuery(name = "Teachers.findAll", query = "SELECT u FROM Teacher u"),
    @NamedQuery(name = "Teacher.byStudyGroupAndRole", query = "SELECT u FROM Teacher u WHERE u.studyGroupidStudyGroup = :studygroup" ),
    @NamedQuery(name = "Teacher.checkLogin", query = "SELECT COUNT(t.login) FROM Teacher t WHERE t.login = :login AND t.password = :password")})
public class Teacher extends Users {
    
    
}
