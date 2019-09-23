package com.zuhlke.dbconnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @desc A singleton database access class for MySQL
 * @author Pritam Patil
 */
public final class MysqlConnect {
    public Connection conn;
    private Statement statement;
    public static MysqlConnect db;
    
    public MysqlConnect() throws IOException {
    	Properties config = new Properties();
    	InputStream input = new FileInputStream("config.properties");
    	config.load(input);
        String url= config.getProperty("url");
        String dbName = config.getProperty("db_name");
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = config.getProperty("username");
        String password = config.getProperty("password");
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     * @throws IOException 
     */
    public static synchronized MysqlConnect getDbCon() throws IOException {
        if ( db == null ) {
            db = new MysqlConnect();
        }
        return db;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
    }
}