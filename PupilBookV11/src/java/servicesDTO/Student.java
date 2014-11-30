package servicesDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Kastan on 11/27/2014.
 */
@XmlRootElement
public class Student {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String login;
    private String password;
    
    public Student() {
    }

    public Student(String firstName, String middleName,String lastName,String phone,String email,String login,String password,int studyGroupID){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.studyGroupID = studyGroupID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int studyGroupID;

    public void setStudyGroupID(int studyGroupID) {
        this.studyGroupID = studyGroupID;
    }

    

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }



    public int getStudyGroupID() {
        return studyGroupID;
    }
}
