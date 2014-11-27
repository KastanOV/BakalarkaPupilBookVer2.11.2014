package src.DBAdapter;

/**
 * Created by Topr on 11/27/2014.
 */
public class Student {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String login;
    private String password;

    private int studyGroupID;

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
