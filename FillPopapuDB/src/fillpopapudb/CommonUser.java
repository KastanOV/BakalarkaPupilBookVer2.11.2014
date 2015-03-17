/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fillpopapudb;

import Entity.HashPassword;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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


    public CommonUser() {
    try
        {
            Open();
        }
        catch (Exception e)
        {
          System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void run()
    {
        Boolean ActualYear = true;
        try{
            for (int i = 0; i < 1 ; i++){
                createSchoolYear(i + 2001, ActualYear);
                ActualYear = false;
            }
        } catch (Exception e){
            
        }
        
    }
    private void createSchoolYear(Integer Year, Boolean actual){
        try{
            Long SchoolYearID;
            this.pstmt = this.sConnection.prepareStatement("INSERT INTO schoolyear (name,isactualyear,StartDate,EndDate) VALUES(?,?,?,?)", pstmt.RETURN_GENERATED_KEYS);
            this.pstmt.setString(1, String.valueOf(Year) + "Test");
            this.pstmt.setBoolean(2, actual);
            this.pstmt.setDate(3, Date.valueOf(Year + "-01-01"));
            this.pstmt.setDate(4, Date.valueOf(Year + "-11-11"));
            int affectedRows = pstmt.executeUpdate();
            this.pstmt.clearParameters();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    SchoolYearID = generatedKeys.getLong(1);
                    this.sConnection.commit();
                    createStudyGroups(SchoolYearID);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch(Exception e){
            System.err.println(e);
        }
    }
    private void createStudyGroups(Long SchoolYearID) throws SQLException, IOException{
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date today = Calendar.getInstance().getTime();        
            String reportDate = df.format(today);
            System.out.println("Report Date: " + reportDate);
            PrintWriter out;
        try {
            out = new PrintWriter("c:\\temp\\" + reportDate + ".txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CommonUser.class.getName()).log(Level.SEVERE, null, ex);
            File f = new File("c:\\temp\\" + reportDate + ".txt");
            f.getParentFile().mkdirs(); 
            f.createNewFile();
            out = new PrintWriter("c:\\temp\\" + reportDate + ".txt");
        }
        for( int i = 0; i < studygroupsNames.length; i++){
            this.pstmt = this.sConnection.prepareStatement("INSERT INTO studygroup(Name,SchoolYear_idSchoolYear) VALUES(?,?)", pstmt.RETURN_GENERATED_KEYS);
            this.pstmt.setString(1, studygroupsNames[i]);
            this.pstmt.setLong(2, SchoolYearID);
            int affectedRows = pstmt.executeUpdate();
            this.pstmt.clearParameters();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long StudyGroupID = generatedKeys.getLong(1);
                    this.sConnection.commit();
                    UploadUsers(StudyGroupID, out);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        out.close();
    }
    private void UploadUsers(Long sgID, PrintWriter out){
        String TeacherLogin = "empty";
        try{
            this.pstmt = this.sConnection.prepareStatement("INSERT INTO users(FirstName,LastName,Phone,Email,Login,Password,StudyGroup_idStudyGroup,Role,deleted)VALUES(?,?,?,?,?,?,?,?,?);");
            int firstTmp = rnd.nextInt(FirstNames.length);
            int lastTmp = rnd.nextInt(LastNames.length);
            this.pstmt.setString(1, FirstNames[firstTmp]);
            this.pstmt.setString(2, LastNames[lastTmp]);
            this.pstmt.setString(3, LastNames[lastTmp]);
            this.pstmt.setString(4, LastNames[lastTmp] + "." + FirstNames[firstTmp] + "@seznam.com");
            this.pstmt.setString(5, LastNames[lastTmp]);
            String passTmp = createPassword();
            this.pstmt.setString(6, HashPassword.md5Hash(passTmp));
            this.pstmt.setLong(7, sgID);
            this.pstmt.setString(8, "T");
            this.pstmt.setBoolean(9, false);
            pstmt.executeUpdate();
            this.pstmt.clearParameters();
            this.sConnection.commit();
            out.printf("\"%s\";\"%s\";\"T\"\n", LastNames[lastTmp], passTmp);
        }catch (Exception e){

        }
        for (int i = 0; i < 30; i++){
            try{
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO users(FirstName,LastName,Phone,Email,Login,Password,StudyGroup_idStudyGroup,Role,deleted)VALUES(?,?,?,?,?,?,?,?,?);");
                int firstTmp = rnd.nextInt(FirstNames.length);
                int lastTmp = rnd.nextInt(LastNames.length);
                this.pstmt.setString(1, FirstNames[firstTmp]);
                this.pstmt.setString(2, LastNames[lastTmp]);
                this.pstmt.setString(3, LastNames[lastTmp]);
                this.pstmt.setString(4, LastNames[lastTmp] + "." + FirstNames[firstTmp] + "@seznam.com");
                this.pstmt.setString(5, LastNames[lastTmp]);
                String passTmp = createPassword();
                this.pstmt.setString(6, HashPassword.md5Hash(passTmp));
                this.pstmt.setLong(7, sgID);
                this.pstmt.setString(8, "S");
                this.pstmt.setBoolean(9, false);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
                out.printf("\"%s\";\"%s\";\"S\"\n", LastNames[lastTmp], passTmp);
                GenerateAttendance(LastNames[lastTmp]);
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO users(FirstName,LastName,Phone,Email,Login,Password,StudyGroup_idStudyGroup,Role,deleted)VALUES(?,?,?,?,?,?,?,?,?);");
                this.pstmt.setString(1, FirstNames[firstTmp]);
                this.pstmt.setString(2, LastNames[lastTmp]);
                this.pstmt.setString(3, LastNames[lastTmp]);
                this.pstmt.setString(4, LastNames[lastTmp] + "." + FirstNames[firstTmp] + "@seznam.com");
                this.pstmt.setString(5, "p" + LastNames[lastTmp]);
                this.pstmt.setString(6, HashPassword.md5Hash(passTmp));
                this.pstmt.setLong(7, sgID);
                this.pstmt.setString(8, "S");
                this.pstmt.setBoolean(9, false);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
                out.printf("\"%s\";\"%s\";\"P\"\n", "p" + LastNames[lastTmp], passTmp);
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO parrentstudent (Student_Login,Parent_Login) VALUES (?,?)");
                this.pstmt.setString(1, LastNames[lastTmp]);
                this.pstmt.setString(2, "p" + LastNames[lastTmp]);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
        }catch (Exception e){

        }
        }
    }
    private String createPassword(){
        char[] symbols;
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        for (char ch = 'A'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
        Random random = new Random();
        
        StringBuilder value = new StringBuilder();

        for (int idx = 0; idx < 10; ++idx) 
            value.append(symbols[random.nextInt(symbols.length)]);
        return value.toString();
    }
    private void GenerateAttendance(String login){
        for(int i = 0; i < rnd.nextInt(50); i++){
            try{
                int year = rnd.nextInt(15);
                int month = rnd.nextInt(11);
                int day = rnd.nextInt(27);
                Boolean b1 = rnd.nextBoolean();
                Boolean b2 = rnd.nextBoolean();
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO attendance(MissingStart,MissingEnd,Excussed,Users_Login)VALUES(?,?,?,?)");
                this.pstmt.setDate(1, Date.valueOf(year +2000 + "-" + month +1  + "-" + day+1));
                if(b1){
                    this.pstmt.setDate(2, Date.valueOf(year +2000 + "-" + month +1+ "-" + day+1));
                }else{
                    this.pstmt.setDate(2, null);
                }
                this.pstmt.setBoolean(3, b2);
                this.pstmt.setString(4,login);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();

            } catch(Exception e){
                //System.err.println(e);
            }
        }
    }
}
