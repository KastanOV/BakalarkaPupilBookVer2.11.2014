/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Topr
 */
@Entity
@Table(name = "informations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informations.findAll", query = "SELECT i FROM Informations i"),
    @NamedQuery(name = "Informations.findByIdinformations", query = "SELECT i FROM Informations i WHERE i.idinformations = :idinformations"),
    @NamedQuery(name = "Informations.findByDescription", query = "SELECT i FROM Informations i WHERE i.description = :description"),
    @NamedQuery(name = "Informations.findByInfoForParrents", query = "SELECT i FROM Informations i WHERE i.infoForParrents = :infoForParrents"),
    @NamedQuery(name = "Informations.findBySomeMessage", query = "SELECT i FROM Informations i WHERE i.someMessage = :someMessage"),
    @NamedQuery(name = "Informations.findByCreateDate", query = "SELECT i FROM Informations i WHERE i.createDate = :createDate")})
public class Informations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idinformations")
    private Integer idinformations;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "InfoForParrents")
    private boolean infoForParrents;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "SomeMessage")
    private String someMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumn(name = "StudyGroup_idStudyGroup", referencedColumnName = "idStudyGroup")
    @ManyToOne
    private Studygroup studyGroupidStudyGroup;
    @JoinColumn(name = "Users_Login", referencedColumnName = "Login")
    @ManyToOne
    private Users usersLogin;

    public Informations() {
    }

    public Informations(Integer idinformations) {
        this.idinformations = idinformations;
    }

    public Informations(Integer idinformations, String description, boolean infoForParrents, String someMessage, Date createDate) {
        this.idinformations = idinformations;
        this.description = description;
        this.infoForParrents = infoForParrents;
        this.someMessage = someMessage;
        this.createDate = createDate;
    }

    public Integer getIdinformations() {
        return idinformations;
    }

    public void setIdinformations(Integer idinformations) {
        this.idinformations = idinformations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getInfoForParrents() {
        return infoForParrents;
    }

    public void setInfoForParrents(boolean infoForParrents) {
        this.infoForParrents = infoForParrents;
    }

    public String getSomeMessage() {
        return someMessage;
    }

    public void setSomeMessage(String someMessage) {
        this.someMessage = someMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Studygroup getStudyGroupidStudyGroup() {
        return studyGroupidStudyGroup;
    }

    public void setStudyGroupidStudyGroup(Studygroup studyGroupidStudyGroup) {
        this.studyGroupidStudyGroup = studyGroupidStudyGroup;
    }

    public Users getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(Users usersLogin) {
        this.usersLogin = usersLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinformations != null ? idinformations.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informations)) {
            return false;
        }
        Informations other = (Informations) object;
        if ((this.idinformations == null && other.idinformations != null) || (this.idinformations != null && !this.idinformations.equals(other.idinformations))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Informations[ idinformations=" + idinformations + " ]";
    }
    
}
