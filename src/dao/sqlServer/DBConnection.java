package sqlServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection dbc = null;
    public static final String DRIVER   = "org.postgresql.Driver";
    public static final String URL      = "jdbc:postgresql://johnny.heliohost.org:5432/shk0307_group6";
    public static final String UID      = "shk0307";
    public static final String PASSWORD = "psql6";

    public Connection con = null;
    public static DBConnection getInstance() {
        if(dbc == null) {
        	dbc = new DBConnection();
        }
        return dbc;
    	
    }
    private DBConnection() {
    	connect();
    }

    
    public void connect() {
        try {
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(URL, UID, PASSWORD);
        } catch(ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
            this.con = null;
        } catch(SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            this.con = null;
        }
    }

    public void disconnect() {
        try {
            this.con.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.con;
    }
    
}