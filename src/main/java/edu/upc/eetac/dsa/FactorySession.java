package edu.upc.eetac.dsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FactorySession {
    public static Session openSession() {


        Connection conn = getConnection();

        Session session = new SessionImpl(conn);

        return session;
    }



    private static Connection getConnection() {
        Connection conn = null;
        String namedb = getString("mysql", "mainDB");
        String username = getString("mysql", "username");
        String password = getString("mysql", "password");
        //instanciar driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=admin1234");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "admin1234");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/" + namedb + "?user=" + username + "&password=" + password +"");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+namedb+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
    private static String getString (String filename, String key){
        HashMap<String, ResourceBundle> mysql = new HashMap<String, ResourceBundle>();

        ResourceBundle rbundle = mysql.get(filename);

        if (rbundle == null){
            rbundle = ResourceBundle.getBundle(filename);
            mysql.put(filename, rbundle);
        }

        return rbundle.getString(key);
    }
}
