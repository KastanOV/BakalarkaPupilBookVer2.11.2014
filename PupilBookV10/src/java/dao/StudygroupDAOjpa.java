/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;

/**
 *
 * @author KastanNotas
 */
class StudygroupDAOjpa {
    
    EntityManager em;
    
    public StudygroupDAOjpa(EntityManager em){
        super();
        this.em = em;
    }
    
    
}
