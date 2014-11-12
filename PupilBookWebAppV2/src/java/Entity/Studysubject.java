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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KastanNotas
 */
@Entity
@Table(name = "studysubject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studysubject.findAll", query = "SELECT s FROM Studysubject s"),
    @NamedQuery(name = "Studysubject.findByIdStudySubject", query = "SELECT s FROM Studysubject s WHERE s.idStudySubject = :idStudySubject"),
    @NamedQuery(name = "Studysubject.findByName", query = "SELECT s FROM Studysubject s WHERE s.name = :name")})
public class Studysubject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStudySubject")
    private Integer idStudySubject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studySubjectidStudySubject")
    private Collection<Sheduleitem> sheduleitemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studySubjectidStudySubject")
    private Collection<TeacherSubjects> teacherSubjectsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studySubjectidStudySubject")
    private Collection<Results> resultsCollection;

    public Studysubject() {
    }

    public Studysubject(Integer idStudySubject) {
        this.idStudySubject = idStudySubject;
    }

    public Studysubject(Integer idStudySubject, String name) {
        this.idStudySubject = idStudySubject;
        this.name = name;
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

    @XmlTransient
    public Collection<Sheduleitem> getSheduleitemCollection() {
        return sheduleitemCollection;
    }

    public void setSheduleitemCollection(Collection<Sheduleitem> sheduleitemCollection) {
        this.sheduleitemCollection = sheduleitemCollection;
    }

    @XmlTransient
    public Collection<TeacherSubjects> getTeacherSubjectsCollection() {
        return teacherSubjectsCollection;
    }

    public void setTeacherSubjectsCollection(Collection<TeacherSubjects> teacherSubjectsCollection) {
        this.teacherSubjectsCollection = teacherSubjectsCollection;
    }

    @XmlTransient
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
