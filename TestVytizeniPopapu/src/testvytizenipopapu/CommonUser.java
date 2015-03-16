/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testvytizenipopapu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaroslav
 */
class CommonUser extends DbTable{
    PreparedStatement pstmt;
    Statement stmt;
    int maxUsers = 10;
    Random rnd = new Random();
    Date today = new Date();

    public CommonUser() {
    try
        {
            Open();
            List<User> UsersTmp = new ArrayList<>();
            this.stmt = this.sConnection.createStatement();
            this.stmt.execute("Select * from users");
            ResultSet rs = this.stmt.getResultSet();
          
          
            while(rs.next()){
                User tmp = new User();
                //Retrieve by column name
                tmp.setLogin(rs.getString("Login"));
                tmp.setPassword(rs.getString("Password"));
                tmp.setRole(rs.getString("Role"));
                tmp.setStudyGroup(rs.getInt("StudyGroup_idStudyGroup"));
                UsersTmp.add(tmp);
            }
            userList = new User[UsersTmp.size()];
            userList = UsersTmp.toArray(userList);
            String nasrat = "nasrat";
        }
        catch (Exception e)
        {
          System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void run()
    {
        long time = System.nanoTime();
        System.out.println("Thread started");
        while (System.nanoTime() - time < 1000000000.0D * DbTable.runningTime) {
            try
            {
                User RandomUser = userList[this.rnd.nextInt(userList.length)];
                this.pstmt = this.sConnection.prepareStatement("Select * from users where Login = ?");

                this.pstmt.setString(1, RandomUser.getLogin());
                this.pstmt.executeQuery();
                this.pstmt.clearParameters();
                ResultSet rs = this.pstmt.getResultSet();
                rs.next();
                User tmp = new User();
                switch (rs.getString("Role")) {
                    case "S" : 
                        tmp.setLogin(rs.getString("Login"));
                        tmp.setPassword(rs.getString("Password"));
                        tmp.setRole(rs.getString("Role"));
                        tmp.setStudyGroup(rs.getInt("StudyGroup_idStudyGroup"));
                        StudentParrentTests(tmp);
                        break;
                    case "P" : 
                        tmp.setLogin(rs.getString("Login"));
                        tmp.setPassword(rs.getString("Password"));
                        tmp.setRole(rs.getString("Role"));
                        tmp.setStudyGroup(rs.getInt("StudyGroup_idStudyGroup"));
                        StudentParrentTests(tmp);
                        break;
                    case "T" :
                        tmp.setLogin(rs.getString("Login"));
                        tmp.setPassword(rs.getString("Password"));
                        tmp.setRole(rs.getString("Role"));

                        break;
                    default : 
                        break;
                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
            try {
                Thread.sleep(this.rnd.nextInt(200));
            } catch (InterruptedException ex) {
                Logger.getLogger(CommonUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        System.out.println("Thread ended");
    }
    private void StudentParrentTests(User u) throws SQLException{
        switch (this.rnd.nextInt(2)){
            case 0 : 
                selectResults1(u);
                break;
            case 1 : 
                selectAttendance1(u);
                break;
            default: System.out.println("Something wrong"); 
        }
        
        
    }
    private void TeacherTests(User u){
        
    }
    private void InsertResult(User u, User t) throws SQLException{
        this.pstmt = this.sConnection.prepareStatement("INSERT INTO results ( Description, Score, Date, Teacher_Login, Student_Login, StudySubject_idStudySubject, SchoolYear_idSchoolYear) VALUES (?,?,?,?,?,?,?)");
        this.pstmt.setString(1, "Thread test data" + String.valueOf(rnd.nextLong()));
        this.pstmt.setInt(2, rnd.nextInt(10));
        this.pstmt.setLong(3, rnd.nextLong());
    }
    private void selectAttendance1(User u) throws SQLException{
        this.pstmt = this.sConnection.prepareStatement("SELECT * FROM attendance a where exists (select * from users u join studygroup on StudyGroup_idStudyGroup = idStudyGroup where idStudyGroup = ? and u.login = a.users_login)");
        this.pstmt.setInt(1, u.getStudyGroup());
        this.pstmt.executeQuery();
        this.pstmt.clearParameters();
        ResultSet rs = this.pstmt.getResultSet();
        while(rs.next()){
            System.out.println("Attendance " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " ");
        }
    }
    private void selectResults1(User u) throws SQLException{
        int SchoolYearId = getActualSchoolYear();
        this.pstmt = this.sConnection.prepareStatement("select * from results WHERE Student_Login = ? AND SchoolYear_idSchoolYear = ? AND StudySubject_idStudySubject = ?");
        this.pstmt.setString(1, u.getLogin());
        this.pstmt.setInt(2, SchoolYearId);
        this.pstmt.setInt(3, u.getStudyGroup());
        this.pstmt.executeQuery();
        this.pstmt.clearParameters();
        ResultSet rs = this.pstmt.getResultSet();
        while(rs.next()){
            System.out.println("Result " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " );
        }
    }
    private int getActualSchoolYear() throws SQLException{
        this.pstmt = this.sConnection.prepareStatement("SELECT idSchoolYear FROM schoolyear WHERE schoolyear.isactualyear = true");
        this.pstmt.executeQuery();
        this.pstmt.clearParameters();       
        ResultSet rs = this.pstmt.getResultSet();
        rs.next();
        return rs.getInt("idSchoolYear");
    }
    
    
}
