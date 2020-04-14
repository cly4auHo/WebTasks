package jsplogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogPasChecker {
    private static final String GET_LOG = "SELECT id FROM users WHERE loggin='";
    private static final String GET_PAS = "' AND password=";
    private static final String NAME_OF_COLUMN = "id";

    public LogPasChecker(){ }

    public boolean printer(String logginToCheck, String passwordToCheck) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/myShop?" + "user=root&password=");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        Statement stmt = null;
        ResultSet rs = null;
        String idCheck = "";
        int id = 0;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_LOG + logginToCheck + GET_PAS + passwordToCheck);
            rs.next();
            idCheck = rs.getString(NAME_OF_COLUMN);

            if (idCheck.length() == 0) {
               id = 0;
            } else {
                id = rs.getInt(NAME_OF_COLUMN);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();         
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();            
                }

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();               
                }

                stmt = null;
            }
        }

        if (id != 0) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
