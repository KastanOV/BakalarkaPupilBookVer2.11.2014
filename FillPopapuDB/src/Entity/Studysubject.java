/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;

public class Studysubject implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idStudySubject;

    private String name;

    private String shortName;

    private Collection<Sheduleitem> sheduleitemCollection;

    private Collection<TeacherSubjects> teacherSubjectsCollection;

    private Collection<Results> resultsCollection;

    public Studysubject() {
    }

    public Studysubject(Integer idStudySubject) {
        this.idStudySubject = idStudySubject;
    }

    public Studysubject(Integer idStudySubject, String name, String shortName) {
        this.idStudySubject = idStudySubject;
        this.name = name;
        this.shortName = shortName;
    }

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
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Collection<Sheduleitem> getSheduleitemCollection() {
        return sheduleitemCollection;
    }

    public void setSheduleitemCollection(Collection<Sheduleitem> sheduleitemCollection) {
        this.sheduleitemCollection = sheduleitemCollection;
    }

    public Collection<TeacherSubjects> getTeacherSubjectsCollection() {
        return teacherSubjectsCollection;
    }

    public void setTeacherSubjectsCollection(Collection<TeacherSubjects> teacherSubjectsCollection) {
        this.teacherSubjectsCollection = teacherSubjectsCollection;
    }

    public Collection<Results> getResultsCollection() {
        return resultsCollection;
    }

    public void setResultsCollection(Collection<Results> resultsCollection) {
        this.resultsCollection = resultsCollection;
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
