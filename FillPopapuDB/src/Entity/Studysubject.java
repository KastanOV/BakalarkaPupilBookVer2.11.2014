/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;


public class Studysubject implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idStudySubject;

    private String name;

    private String shortName;

    private String[] desc;

    public String[] getDesc() {
        return desc;
    }

    public void setDesc(String[] desc) {
        this.desc = desc;
    }

    public Studysubject(Long idStudySubject, String name, String shortName, String[] desc) {
        this.idStudySubject = idStudySubject;
        this.name = name;
        this.shortName = shortName;
        this.desc = desc;
    }

    public Studysubject() {
    }

    public Studysubject(Long idStudySubject) {
        this.idStudySubject = idStudySubject;
    }

    public Studysubject(Long idStudySubject, String name, String shortName) {
        this.idStudySubject = idStudySubject;
        this.name = name;
        this.shortName = shortName;
    }

    public Long getIdStudySubject() {
        return idStudySubject;
    }

    public void setIdStudySubject(Long idStudySubject) {
        this.idStudySubject = idStudySubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStudySubject != null ? idStudySubject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studysubject)) {
            return false;
        }
        Studysubject other = (Studysubject) object;
        if ((this.idStudySubject == null && other.idStudySubject != null) || (this.idStudySubject != null && !this.idStudySubject.equals(other.idStudySubject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Studysubject[ idStudySubject=" + idStudySubject + " ]";
    }
    
}
