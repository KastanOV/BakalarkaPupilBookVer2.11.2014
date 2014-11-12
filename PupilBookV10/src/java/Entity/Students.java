/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author KastanNotas
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Students.findAll", query = "SELECT u FROM Users u where u.role = :" + 'S'),
    @NamedQuery(name = "Students.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName and u.role = :" + 'S'),
    @NamedQuery(name = "Students.findByMiddleName", query = "SELECT u FROM Users u WHERE u.middleName = :middleName and u.role = :" + 'S'),
    @NamedQuery(name = "Students.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName and u.role = :" + 'S'),
    @NamedQuery(name = "Students.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone and u.role = :" + 'S'),
    @NamedQuery(name = "Students.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email and u.role = :" + 'S'),
    @NamedQuery(name = "Students.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login and u.role = :" + 'S'),
    @NamedQuery(name = "Students.findByBirthDate", query = "SELECT u FROM Users u WHERE u.birthDate = :birthDate and u.role = :" + 'S'),
    @NamedQuery(name = "Students.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role and u.role = :" + 'S')})
public class Students implements Serializable {
    private static final long serialVersionUID = 1L;
   @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FirstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "MiddleName")
    private String middleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LastName")
    private String lastName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "Email")
    private String email;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Password")
    private String password;
    @Column(name = "BirthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Role")
    private Character role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersLogin")
    private Collection<Sheduleitem> sheduleitemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentLogin")
    private Collection<Parrentstudent> parrentstudentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentLogin")
    private Collection<Parrentstudent> parrentstudentCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersLogin")
    private Collection<TeacherSubjects> teacherSubjectsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacherLogin")
    private Collection<Results> resultsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentLogin")
    private Collection<Results> resultsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersLogin")
    private Collection<Attendance> attendanceCollection;
    @JoinColumn(name = "StudyGroup_idStudyGroup", referencedColumnName = "idStudyGroup")
    @ManyToOne
    private Studygroup studyGroupidStudyGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersLogin")
    private Collection<Informations> informationsCollection;

    public Students() {
    }

    public Students(String login) {
        this.login = login;
    }

    public Students(String login, String firstName, String lastName, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = 'S';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Character getRole() {
        return role;
    }

    @XmlTransient
    public Collection<Sheduleitem> getSheduleitemCollection() {
        return sheduleitemCollection;
    }

    public void setSheduleitemCollection(Collection<Sheduleitem> sheduleitemCollection) {
        this.sheduleitemCollection = sheduleitemCollection;
    }

    @XmlTransient
    public Collection<Parrentstudent> getParrentstudentCollection() {
        return parrentstudentCollection;
    }

    public void setParrentstudentCollection(Collection<Parrentstudent> parrentstudentCollection) {
        this.parrentstudentCollection = parrentstudentCollection;
    }

    @XmlTransient
    public Collection<Parrentstudent> getParrentstudentCollection1() {
        return parrentstudentCollection1;
    }

    public void setParrentstudentCollection1(Collection<Parrentstudent> parrentstudentCollection1) {
        this.parrentstudentCollection1 = parrentstudentCollection1;
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

    @XmlTransient
    public Collection<Results> getResultsCollection1() {
        return resultsCollection1;
    }

    public void setResultsCollection1(Collection<Results> resultsCollection1) {
        this.resultsCollection1 = resultsCollection1;
    }

    @XmlTransient
    public Collection<Attendance> getAttendanceCollection() {
        return attendanceCollection;
    }

    public void setAttendanceCollection(Collection<Attendance> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    public Studygroup getStudyGroupidStudyGroup() {
        return studyGroupidStudyGroup;
    }

    public void setStudyGroupidStudyGroup(Studygroup studyGroupidStudyGroup) {
        this.studyGroupidStudyGroup = studyGroupidStudyGroup;
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
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Students)) {
            return false;
        }
        Students other = (Students) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Users[ login=" + login + " ]";
    }
}
