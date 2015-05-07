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
    Long ActualYearN = 0l;

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
        GenerateAdmin();
        createStudySubject();
        try {
            GenerateTeachers();
        } catch (IOException ex) {
            Logger.getLogger(CommonUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            for (int i = 0; i < 3 ; i++){
                
                GenerateSchoolYear(i + 2001, ActualYear);
                ActualYear = false;
            }
        } catch (Exception e){
            
        }
        
    }
    private void GenerateInformation(String StudentLogin, Long sgID){
        for(int i = 0; i < rnd.nextInt(5); i++){
            String tmps = Lorum[rnd.nextInt(Lorum.length)].substring(0, 19);
            String tmpl = Lorum[rnd.nextInt(Lorum.length)];
            Boolean forsg = rnd.nextBoolean();
            try{
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO informations(Description,InfoForParrents,SomeMessage,CreateDate,StudyGroup_idStudyGroup,Users_Login,Teacher_Login)VALUES(?,?,?,?,?,?,?)");
                this.pstmt.setString(1, tmps);
                this.pstmt.setBoolean(2, rnd.nextBoolean());
                this.pstmt.setString(3, tmpl);
                this.pstmt.setDate(4, new Date(1346524199000l));
                if(forsg){
                    this.pstmt.setLong(5, sgID);
                    this.pstmt.setString(6,null);
                }else {
                    this.pstmt.setString(5, null);
                    this.pstmt.setString(6,StudentLogin);
                }
                this.pstmt.setString(7, TeachersLogins[rnd.nextInt(TeachersLogins.length)]);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
            }catch(Exception e){
                System.err.println(e);
            }
        }
    }
    private void GenerateSheduleItems(Long StudyGroupID){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 8; j++){
                try{
                    this.pstmt = this.sConnection.prepareStatement("INSERT INTO sheduleitem(day,hour,StudyGroup_idStudyGroup,Users_Login,StudySubject_idStudySubject)VALUES(?,?,?,?,?)");
                    this.pstmt.setInt(1, i);
                    this.pstmt.setInt(2, j);
                    pstmt.setLong(3, StudyGroupID);
                    pstmt.setString(4, TeachersLogins[rnd.nextInt(TeachersLogins.length)]);
                    pstmt.setLong(5, ss[rnd.nextInt(12)].getIdStudySubject());
                    pstmt.executeUpdate();
                    this.pstmt.clearParameters();
                    this.sConnection.commit();
                }catch(Exception e){
                    System.err.println(e);
                }
            }
        }
    }
    private void createStudySubject(){
        for(int i = 0 ; i < ss.length; i++){
            try{
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO studysubject(Name,ShortName)VALUES(?,?)", pstmt.RETURN_GENERATED_KEYS);
                this.pstmt.setString(1, ss[i].getName());
                this.pstmt.setString(2, ss[i].getShortName());
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ss[i].setIdStudySubject(generatedKeys.getLong(1));
                    this.sConnection.commit();
                    }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            }catch(Exception e){
                System.err.println(e);
            }
        }
        
    }
    private void GenerateSchoolYear(Integer Year, Boolean actual){
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
                    ActualYearN = SchoolYearID;
                    this.sConnection.commit();
                    GenerateStudyGroups(SchoolYearID);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            
        } catch(Exception e){
            System.err.println(e);
        }
    }
    private void GenerateStudyGroups(Long SchoolYearID) throws SQLException, IOException{
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date today = Calendar.getInstance().getTime();        
            String reportDate = df.format(today);
            System.out.println("Report Date: " + reportDate);
            PrintWriter out;
        try {
            out = new PrintWriter("c:\\temp\\Students" + reportDate + ".csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CommonUser.class.getName()).log(Level.SEVERE, null, ex);
            File f = new File("c:\\temp\\Students" + reportDate + ".csv");
            f.getParentFile().mkdirs(); 
            f.createNewFile();
            out = new PrintWriter("c:\\temp\\Students" + reportDate + ".csv");
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
                    GenerateUsers(StudyGroupID, out);
                    GenerateSheduleItems(StudyGroupID);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        out.close();
    }
    private void GenerateTeachers() throws IOException{
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date today = Calendar.getInstance().getTime();        
            String reportDate = df.format(today);
            System.out.println("Report Date: " + reportDate);
            PrintWriter out;
        try {
            out = new PrintWriter("c:\\temp\\Teachers" + reportDate + ".csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CommonUser.class.getName()).log(Level.SEVERE, null, ex);
            File f = new File("c:\\temp\\Students" + reportDate + ".csv");
            f.getParentFile().mkdirs(); 
            f.createNewFile();
            out = new PrintWriter("c:\\temp\\Teachers" + reportDate + ".csv");
        }
        TeachersLogins = new String[20];
        for(int i = 0; i < 20; i++){
            try{
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO users(FirstName,LastName,Phone,Email,Login,Password,Role,deleted)VALUES(?,?,?,?,?,?,?,?);");
                int firstTmp = rnd.nextInt(FirstNames.length);
                int lastTmp = rnd.nextInt(LastNames.length);
                this.pstmt.setString(1, FirstNames[firstTmp]);
                this.pstmt.setString(2, LastNames[lastTmp]);
                this.pstmt.setString(3, LastNames[lastTmp]);
                this.pstmt.setString(4, LastNames[lastTmp] + "." + FirstNames[firstTmp] + "@seznam.com");
                this.pstmt.setString(5, LastNames[lastTmp]);
                String passTmp = createPassword();
                this.pstmt.setString(6, HashPassword.md5Hash(LastNames[lastTmp]));
                this.pstmt.setString(7, "T");
                this.pstmt.setBoolean(8, false);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
                out.printf("%s;%s;%s\n", LastNames[lastTmp], LastNames[lastTmp], HashPassword.md5Hash(LastNames[lastTmp]));
                TeachersLogins[i] = LastNames[lastTmp];
            }catch (Exception e){

            }
        }
        out.close();
    }
    private void GenerateAdmin(){
        try{
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO users(FirstName,LastName,Phone,Email,Login,Password,Role,deleted)VALUES(?,?,?,?,?,?,?,?);");
                int firstTmp = rnd.nextInt(FirstNames.length);
                int lastTmp = rnd.nextInt(LastNames.length);
                this.pstmt.setString(1, "admin");
                this.pstmt.setString(2, "admin");
                this.pstmt.setString(3, "admin");
                this.pstmt.setString(4, "admin");
                this.pstmt.setString(5, "admin");
                String passTmp = "admin";
                this.pstmt.setString(6, HashPassword.md5Hash(passTmp));
                this.pstmt.setString(7, "A");
                this.pstmt.setBoolean(8, false);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
            }catch (Exception e){

            }
    }
    private void GenerateUsers(Long sgID, PrintWriter out){
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
                this.pstmt.setString(6, HashPassword.md5Hash(LastNames[lastTmp]));
                this.pstmt.setLong(7, sgID);
                this.pstmt.setString(8, "S");
                this.pstmt.setBoolean(9, false);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
                out.printf("%s;%s;%s;%s;S\n", LastNames[lastTmp], LastNames[lastTmp], HashPassword.md5Hash(LastNames[lastTmp]), String.valueOf(sgID) );
                GenerateAttendance(LastNames[lastTmp]);
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO users(FirstName,LastName,Phone,Email,Login,Password,StudyGroup_idStudyGroup,Role,deleted)VALUES(?,?,?,?,?,?,?,?,?);");
                this.pstmt.setString(1, FirstNames[firstTmp]);
                this.pstmt.setString(2, LastNames[lastTmp]);
                this.pstmt.setString(3, LastNames[lastTmp]);
                this.pstmt.setString(4, LastNames[lastTmp] + "." + FirstNames[firstTmp] + "@seznam.com");
                this.pstmt.setString(5, "p" + LastNames[lastTmp]);
                this.pstmt.setString(6, HashPassword.md5Hash(LastNames[lastTmp]));
                this.pstmt.setLong(7, sgID);
                this.pstmt.setString(8, "P");
                this.pstmt.setBoolean(9, false);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
                out.printf("%s;%s;%s;%s;P\n", LastNames[lastTmp], LastNames[lastTmp], HashPassword.md5Hash(LastNames[lastTmp]), String.valueOf(sgID) );
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO parrentstudent (Student_Login,Parent_Login) VALUES (?,?)");
                this.pstmt.setString(1, LastNames[lastTmp]);
                this.pstmt.setString(2, "p" + LastNames[lastTmp]);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
                GenerateResults(LastNames[lastTmp]);
                GenerateInformation(LastNames[lastTmp],sgID);
        }catch (Exception e){
            System.err.println(e);
        }
        }
    }
    private void GenerateResults(String StudentLogin){
        for(int i = 0; i < rnd.nextInt(1500); i++){
            try{
                int sstmp = rnd.nextInt(ss.length);
                this.pstmt = this.sConnection.prepareStatement("INSERT INTO results(Description,Score,Date,Teacher_Login,Student_Login,StudySubject_idStudySubject,SchoolYear_idSchoolYear)VALUES(?,?,?,?,?,?,?)");
                this.pstmt.setString(1, ss[sstmp].getDesc()[rnd.nextInt(ss[sstmp].getDesc().length)]);
                this.pstmt.setInt(2, rnd.nextInt(10));
                this.pstmt.setDate(3, new Date(1346524199000l));
                this.pstmt.setString(4, TeachersLogins[rnd.nextInt(TeachersLogins.length)]);
                this.pstmt.setString(5, StudentLogin);
                this.pstmt.setLong(6, ss[sstmp].getIdStudySubject());
                this.pstmt.setLong(7, ActualYearN);
                pstmt.executeUpdate();
                this.pstmt.clearParameters();
                this.sConnection.commit();
            }catch(Exception e){
                System.err.println(e);
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
                this.pstmt.setDate(1, new Date(1346524199000l));
                if(b1){
                    this.pstmt.setDate(2, new Date(1346524199000l));
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
