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
@DiscriminatorValue(value = "T")
@NamedQueries({
    @NamedQuery(name = "Teachers.findAll", query = "SELECT u FROM Teacher u"),
    @NamedQuery(name = "Teacher.byStudyGroupAndRole", query = "SELECT u FROM Teacher u WHERE u.studyGroupidStudyGroup = :studygroup" )})
public class Teacher extends Users {
    
    
}
