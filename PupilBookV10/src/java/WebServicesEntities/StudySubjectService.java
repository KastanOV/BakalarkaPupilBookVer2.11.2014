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
public class StudySubjectService implements Serializable {
    
    @Id
    @Column(name = "idStudySubject")
    private Integer idStudySubject;
    @Column(name = "Name")
    private String name;
    @Column(name = "ShortName")
    private String ShortName;

    public Integer getIdStudySubject() {
        return idStudySubject;
    }

    public void setIdStudySubject(Integer idStudySubject) {
        this.idStudySubject = idStudySubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }
}
