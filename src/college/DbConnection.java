/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package college;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author theprophet
 */
public class DbConnection {
    
    public Connection connect(){
        try {
            String url = "jdbc:mysql://localhost:3306/college_management?useSSL=false";
            String user = "naveen";
            String password = "naveen";
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("Database Connection Established");
            return conn;
        } 
        
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
