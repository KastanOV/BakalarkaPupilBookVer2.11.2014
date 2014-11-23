/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "studygroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studygroup.findAll", query = "SELECT s FROM Studygroup s"),
    @NamedQuery(name = "Studygroup.findByIdStudyGroup", query = "SELECT s FROM Studygroup s WHERE s.idStudyGroup = :idStudyGroup"),
    @NamedQuery(name = "Studygroup.findByName", query = "SELECT s FROM Studygroup s WHERE s.name = :name")})
public class Studygroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStudyGroup")
    private Integer idStudyGroup;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyGroupidStudyGroup")
    private Collection<Sheduleitem> sheduleitemCollection;
    @JoinColumn(name = "SchoolYear_idSchoolYear", referencedColumnName = "idSchoolYear")
    @ManyToOne(optional = false)
    private Schoolyear schoolYearidSchoolYear;
    @OneToMany(mappedBy = "studyGroupidStudyGroup")
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "studyGroupidStudyGroup")
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

    @XmlTransient
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

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
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
