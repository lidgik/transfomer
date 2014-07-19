package com.bodejidi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseManager{
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public DatabaseManager createDatebaseConnectionAndExecute(String sql){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex)
        {
            //ignore;
        }
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root" + "&password=");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch(SQLException sqle){
            sqle.printStackTrace();
        }
        
        return this;
    }
    
    public void close(){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException sqle){
                //ignore;
            }
        }
        
        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException sqle){
                //ignore;
            }
        }
        
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException sqle){
                //ignore;
            }
        }
    }
}
