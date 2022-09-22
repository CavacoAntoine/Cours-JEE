/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.testjsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.servlet.UnavailableException;

/**
 *
 * @author extra
 */
public class DBConnection {
    
    private static DBConnection instance = null;
    
    private String url = "jdbc:mysql://localhost:3306/jee";
    private String user = "root";
    private String password = "root";
    private boolean isConnected = false;
    private Connection connection = null;
    
    public static DBConnection getInstance() throws UnavailableException {
        if(instance == null ){
            instance = new DBConnection();
            return instance;
        }
        else return instance;
    }
    
    private DBConnection() throws UnavailableException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            throw new UnavailableException("Le driver n'est pas trouvé dans le classpath.");
        }
    }
    
    private Connection createConnection() throws SQLException  {
        if(isConnected) return this.connection;
        else {
            try {
                this.connection = DriverManager.getConnection(url, user, password);
                this.isConnected = true;
                return this.connection;
            }catch (SQLException sqle) {
                throw new SQLException("Erreur de connexion à la base de donnée", sqle);
            }
        }
    }
    
    public void insertStatement(List<String> values, String table) throws SQLException{
        String statement = "INSERT INTO " + table + " VALUES (";
        boolean start = true;
        for(String value : values){
            if(start) {
                statement += "'" + value + "'";
                start = false;
            }
            else statement += ", '" + value + "'";
        }
        
        statement+= ")";
        
        try {
            this.connection = this.createConnection();
            Statement insertStatement = connection.createStatement();
            insertStatement.executeUpdate(statement);
        } catch (SQLException sqle) {
            throw new SQLException("Erreur d'insertion dans la base de donnée", sqle);
        } finally {
            this.close();
        }
    }
    
    public ResultSet selectStatement(Map<String, String> conditions, String table) throws SQLException{
        String statement = "SELECT * FROM " + table + " WHERE ";
        boolean start = true;
        for(String key : conditions.keySet()){
            if(start) {
                statement += key + "=" + "'" + conditions.get(key) + "'";
                start = false;
            }
            else statement += " AND " + key + "=" + conditions.get(key);
        }
        
        try {
            this.connection = this.createConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet result = selectStatement.executeQuery(statement);
            return result;
        } catch (SQLException sqle) {
            throw new SQLException("Erreur d'insertion dans la base de donnée", sqle);
        }
    }
    
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                this.isConnected = false;
                this.connection = null;
            } catch (SQLException sqle) {
                // Nothing to do
            }
        }
    }
    
}
