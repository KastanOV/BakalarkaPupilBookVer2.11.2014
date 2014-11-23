/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServicesEntities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author Topr
 */
@Entity
public class StudyGroupService implements Serializable{
    @Id
    @Column(name = "idStudyGroup")
    private Integer idStudyGroup;
    @Column(name = "Name")
    private String name;
    
    public Integer getIdStudyGroup() {
        return idStudyGroup;
    }

    public void setIdStudyGroup(Integer idStudyGroup) {
        this.idStudyGroup = idStudyGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
