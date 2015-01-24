/*
 * Copyright (C) 2015 Topr
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package Entity;

import Entity.Studygroup;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "schoolyear")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schoolyear.findAll", query = "SELECT s FROM Schoolyear s"),
    @NamedQuery(name = "Schoolyear.findByIdSchoolYear", query = "SELECT s FROM Schoolyear s WHERE s.idSchoolYear = :idSchoolYear"),
    @NamedQuery(name = "Schoolyear.findByName", query = "SELECT s FROM Schoolyear s WHERE s.name = :name"),
    @NamedQuery(name = "Schoolyear.findByIsactualyear", query = "SELECT s FROM Schoolyear s WHERE s.isactualyear = :isactualyear"),
    @NamedQuery(name = "Schoolyear.findByStartDate", query = "SELECT s FROM Schoolyear s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "Schoolyear.findByEndDate", query = "SELECT s FROM Schoolyear s WHERE s.endDate = :endDate")})
public class Schoolyear implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSchoolYear")
    private Integer idSchoolYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactualyear")
    private boolean isactualyear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolYearidSchoolYear")
    private Collection<Studygroup> studygroupCollection;

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

    @XmlTransient
    public Collection<Studygroup> getStudygroupCollection() {
        return studygroupCollection;
    }

    public void setStudygroupCollection(Collection<Studygroup> studygroupCollection) {
        this.studygroupCollection = studygroupCollection;
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
        return "Entity2.Schoolyear[ idSchoolYear=" + idSchoolYear + " ]";
    }
    
}
