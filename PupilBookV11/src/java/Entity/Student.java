/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.Table;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "Users")
@DiscriminatorValue(value = "S")
public class Student extends Users{
    
    
}
