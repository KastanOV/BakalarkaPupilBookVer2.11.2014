/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fillpopapudb;

import Entity.Studysubject;
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
    private static final String[] descM = {"Zlomky sčítání", "Zlomky odčítání", "Malá násobilka", "Velká násobilka", "Rovnice", "Nerovnice"};
    private static final String[] descCJ = {"Karel Hynek", "Petr Hynek", "Lojza Hynek", "Diktát", "Tvrdé I", "Měkké Y"};
    private static final String[] descEN = {"Carl Hynek", "Pítr Hynek", "John Hynek", "Dictat", "Předložky The", "Předložky A"};
    private static final String[] descNE = {"Nemec Hynek", "Bundeswer Hynek", "Adolf Hynek", "Neco nemeckého", "Nemecké I", "Nemecké Y"};
    private static final String[] descFR = {"žvaž Hynek", "žvatll Hynek", "žabožrout Hynek", "žžžžžž", "Tvrdé Ž", "Měkké Ž"};
    private static final String[] descIT = {"Komprende Hynek", "Italský Hynek", "Alpacino Hynek", "IIIII", "Tvrdé U", "Měkké K"};
    private static final String[] descPR = {"Stromy", "Tráva", "Brouci", "Koně", "Pimpas", "Keře"};
    private static final String[] descVL = {"Praha", "Brno", "Baník", "Ostrava", "Plzeň", "Hlučín"};
    private static final String[] descZ = {"Amerika Západní", "Amerika Východní", "Jižní německo", "Serverní Rakousko", "Islamský Stát", "Agresivní Rusové"};
    private static final String[] descON = {"Nepřítel národa", "Kalousek", "Zeman", "Vašík Klausů", "Nepřátelé z východu", "Přátelé ze západu"};
    private static final String[] descV = {"Malba Tuškou", "Malba Tuží", "Malba Perem", "Malba Vodovkami", "Malba Temperami", "Malba Zadkem"};
    private static final String[] descTV = {"Skok přes laďku", "Skok na Laďku", "Skok na Láďu", "Skok přes Láďu", "Skok přes kozu", "Skok na kozy"};
    private static final String[] descSach = {"Hra1", "Hra2", "Hra3", "Hra4", "Hra5", "Hra6"};
    public static String[] studygroupsNames = { "1.A", "1.B", "1.C", "2.A", "2.B", "2.C","3.A", "3.B", "3.C","4.A", "4.B", "4.C","5.A", "5.B", "5.C","6.A", "6.B", "6.C","7.A", "7.B", "7.C","8.A", "8.B", "8.C","9.A", "9.B", "9.C"};
    public static Studysubject[] ss = {new Studysubject(null, "Matematika", "M", descM), new Studysubject(null, "Český jazyk", "ČJ", descCJ),
        new Studysubject(null, "Angličtina", "EN", descEN),new Studysubject(null, "Němčina", "NE", descNE),new Studysubject(null, "Francouština", "FR", descFR),
        new Studysubject(null, "Italština", "IT",descIT),new Studysubject(null, "Přírodověda", "PŘ", descPR),new Studysubject(null, "Vlastivěda", "VL", descVL),
        new Studysubject(null, "Zeměpis", "Z", descZ),new Studysubject(null, "Občanská Nauka", "ON", descON),new Studysubject(null, "Výtvarná výchova", "V", descV),
        new Studysubject(null, "Tělocvik", "TV", descTV),new Studysubject(null, "Šachy", "Sach",descSach)};
    public String[] LastNames;
    public String[] FirstNames;
    public String[] TeachersLogins;
    public String[] Lorum;
     
    public void Open() throws Exception
    {
        createNames();
        if (this.sConnection == null)
        {
            String url = "jdbc:mysql://192.168.1.216:3306/pupilbooktests";
            //String url = "jdbc:mysql://192.168.1.105:3306/pupilbooktests";
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
        BufferedReader br3 = null;
		try {
			String sCurrentLine;
                        List<String> LastNamesTmp = new ArrayList<>();
                        List<String> FirstNamesTmp = new ArrayList<>();
                        List<String> LorumTmp = new ArrayList<>();
			br1 = new BufferedReader(new FileReader("C:\\temp\\LastNames.csv"));
                        br2 = new BufferedReader(new FileReader("C:\\temp\\FirstNames.csv"));
                        br3 = new BufferedReader(new FileReader("C:\\temp\\lorum.txt"));
			while ((sCurrentLine = br1.readLine()) != null) {
                            LastNamesTmp.add(sCurrentLine);
			}
                        while ((sCurrentLine = br2.readLine()) != null) {
                            
                            FirstNamesTmp.add(sCurrentLine);
			}
                        while ((sCurrentLine = br3.readLine()) != null) {
                            if(!sCurrentLine.equals("")){
                                LorumTmp.add(sCurrentLine);
                            }
			}
                        LastNames = new String[LastNamesTmp.size()];
                        FirstNames = new String[FirstNamesTmp.size()];
                        Lorum = new String[LorumTmp.size()];
                        LastNames = LastNamesTmp.toArray(LastNames);
                        FirstNames = FirstNamesTmp.toArray(LastNames);
                        Lorum = LorumTmp.toArray(Lorum);
                        
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
    

