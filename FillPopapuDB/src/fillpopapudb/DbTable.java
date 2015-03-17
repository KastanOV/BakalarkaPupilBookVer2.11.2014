/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fillpopapudb;

import Entity.Studygroup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaroslav
 */
public class DbTable extends Thread{
    static int userCount = 60;
    static double runningTime = 60;
    protected Connection sConnection = null;
    public static String[] studygroupsNames = { "1.A", "1.B", "1.C", "2.A", "2.B", "2.C","3.A", "3.B", "3.C","4.A", "4.B", "4.C","5.A", "5.B", "5.C","6.A", "6.B", "6.C","7.A", "7.B", "7.C","8.A", "8.B", "8.C","9.A", "9.B", "9.C"};
    public String[] LastNames;
    public String[] FirstNames;
     
    public void Open() throws Exception
    {
        createNames();
        if (this.sConnection == null)
        {
            String url = "jdbc:mysql://192.168.56.102:3306/pupilbooktests";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.sConnection = DriverManager.getConnection(url, "kastan", "lithium1");
            this.sConnection.setAutoCommit(false);
            this.sConnection.setTransactionIsolation(8);
        }
    }

    public void Close() throws SQLException
    {
        if (this.sConnection != null)
        {
            this.sConnection.close();
            this.sConnection = null;
        }
    }
    public void createNames(){
        BufferedReader br1 = null;
        BufferedReader br2 = null;
 
		try {
 
			String sCurrentLine;
                        List<String> LastNamesTmp = new ArrayList<>();
                        List<String> FirstNamesTmp = new ArrayList<>();
			br1 = new BufferedReader(new FileReader("C:\\temp\\LastNames.csv"));
                        br2 = new BufferedReader(new FileReader("C:\\temp\\FirstNames.csv"));
			while ((sCurrentLine = br1.readLine()) != null) {
                            LastNamesTmp.add(sCurrentLine);
			}
                        while ((sCurrentLine = br2.readLine()) != null) {
                            FirstNamesTmp.add(sCurrentLine);
			}
                        LastNames = new String[LastNamesTmp.size()];
                        FirstNames = new String[FirstNamesTmp.size()];
                        LastNames = LastNamesTmp.toArray(LastNames);
                        FirstNames = FirstNamesTmp.toArray(LastNames);
                        
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br1 != null)br1.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
                        try {
				if (br2 != null)br2.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }
}
    

