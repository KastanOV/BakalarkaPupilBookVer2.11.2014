/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Topr
 */

public class Studygroup implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idStudyGroup;

    private String name;

    private Collection<Sheduleitem> sheduleitemCollection;
    private Schoolyear schoolYearidSchoolYear;
    private Collection<Users> usersCollection;
    private Collection<Informations> informationsCollection;

    public Studygroup() {
    }

    public Studygroup(Integer idStudyGroup) {
        this.idStudyGroup = idStudyGroup;
    }

    public Studygroup(Integer idStudyGroup, String name) {
        this.idStudyGroup = idStudyGroup;
        this.name = name;
    }

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


    public Collection<Sheduleitem> getSheduleitemCollection() {
        return sheduleitemCollection;
    }

    public void setSheduleitemCollection(Collection<Sheduleitem> sheduleitemCollection) {
        this.sheduleitemCollection = sheduleitemCollection;
    }

    public Schoolyear getSchoolYearidSchoolYear() {
        return schoolYearidSchoolYear;
    }

    public void setSchoolYearidSchoolYear(Schoolyear schoolYearidSchoolYear) {
        this.schoolYearidSchoolYear = schoolYearidSchoolYear;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Collection<Informations> getInformationsCollection() {
        return informationsCollection;
    }

    public void setInformationsCollection(Collection<Informations> informationsCollection) {
        this.informationsCollection = informationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStudyGroup != null ? idStudyGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studygroup)) {
            return false;
        }
        Studygroup other = (Studygroup) object;
        if ((this.idStudyGroup == null && other.idStudyGroup != null) || (this.idStudyGroup != null && !this.idStudyGroup.equals(other.idStudyGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Studygroup[ idStudyGroup=" + idStudyGroup + " ]";
    }
    
}
