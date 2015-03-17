/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


/**
 *
 * @author Topr
 */

public class Schoolyear implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idSchoolYear;

    private String name;

    private boolean isactualyear;

    private Date startDate;

    private Date endDate;

    private Collection<Studygroup> studygroupCollection;

    private Collection<Results> resultsCollection;

    public Schoolyear() {
    }

    public Schoolyear(Integer idSchoolYear) {
        this.idSchoolYear = idSchoolYear;
    }

    public Schoolyear(Integer idSchoolYear, String name, boolean isactualyear, Date startDate, Date endDate) {
        this.idSchoolYear = idSchoolYear;
        this.name = name;
        this.isactualyear = isactualyear;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getIdSchoolYear() {
        return idSchoolYear;
    }

    public void setIdSchoolYear(Integer idSchoolYear) {
        this.idSchoolYear = idSchoolYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsactualyear() {
        return isactualyear;
    }

    public void setIsactualyear(boolean isactualyear) {
        this.isactualyear = isactualyear;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Collection<Studygroup> getStudygroupCollection() {
        return studygroupCollection;
    }

    public void setStudygroupCollection(Collection<Studygroup> studygroupCollection) {
        this.studygroupCollection = studygroupCollection;
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
        hash += (idSchoolYear != null ? idSchoolYear.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schoolyear)) {
            return false;
        }
        Schoolyear other = (Schoolyear) object;
        if ((this.idSchoolYear == null && other.idSchoolYear != null) || (this.idSchoolYear != null && !this.idSchoolYear.equals(other.idSchoolYear))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Schoolyear[ idSchoolYear=" + idSchoolYear + " ]";
    }
    
}
