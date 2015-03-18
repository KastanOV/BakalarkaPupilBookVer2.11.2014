/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testvytizenipopapu;

import java.sql.*;

/**
 *
 * @author Jaroslav
 */
public class DbTable extends Thread{
    static int userCount = 60;
    static double runningTime = 60;
    protected Connection sConnection = null;
    public User[] userList;
     
    public void Open() throws Exception
    {
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
}
    

