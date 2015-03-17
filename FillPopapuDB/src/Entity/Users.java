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
 * @author KastanNotas
 */

public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;

    private String middleName;
    private String lastName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation

    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation

    private String email;
 
    private String login;

    private String password;

    private Date birthDate;

    private Boolean deleted;
            

    private Collection<Sheduleitem> sheduleitemCollection;

    private Collection<Parrentstudent> parrentstudentCollection;

    private Collection<Parrentstudent> parrentstudentCollection1;

    private Collection<TeacherSubjects> teacherSubjectsCollection;

    private Collection<Results> resultsCollection;

    private Collection<Results> resultsCollection1;

    private Collection<Attendance> attendanceCollection;
    

    private Studygroup studyGroupidStudyGroup;

    private Collection<Informations> informationsCollection;

    private Collection<Informations> informationsCollection1;

    public Users() {
    }

    public Users(String login) {
        this.login = login;
    }

    public Users(String login, String firstName, String lastName, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        
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
        if(!password.equals(this.password)){
            this.password = HashPassword.md5Hash(password);
            return;
        }
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public Collection<Sheduleitem> getSheduleitemCollection() {
        return sheduleitemCollection;
    }

    public void setSheduleitemCollection(Collection<Sheduleitem> sheduleitemCollection) {
        this.sheduleitemCollection = sheduleitemCollection;
    }


    public Collection<Parrentstudent> getParrentstudentCollection() {
        return parrentstudentCollection;
    }

    public void setParrentstudentCollection(Collection<Parrentstudent> parrentstudentCollection) {
        this.parrentstudentCollection = parrentstudentCollection;
    }


    public Collection<Parrentstudent> getParrentstudentCollection1() {
        return parrentstudentCollection1;
    }

    public void setParrentstudentCollection1(Collection<Parrentstudent> parrentstudentCollection1) {
        this.parrentstudentCollection1 = parrentstudentCollection1;
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


    public Collection<Results> getResultsCollection1() {
        return resultsCollection1;
    }

    public void setResultsCollection1(Collection<Results> resultsCollection1) {
        this.resultsCollection1 = resultsCollection1;
    }


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


    public Collection<Informations> getInformationsCollection() {
        return informationsCollection;
    }

    public void setInformationsCollection(Collection<Informations> informationsCollection) {
        this.informationsCollection = informationsCollection;
    }
    

    public Collection<Informations> getInformationsCollection1() {
        return informationsCollection1;
    }

    public void setInformationsCollection1(Collection<Informations> informationsCollection1) {
        this.informationsCollection1 = informationsCollection1;
    }
    
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
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
