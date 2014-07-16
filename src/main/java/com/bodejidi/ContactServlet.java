package com.bodejidi;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ContactServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 	throws IOException, ServletException{
    
    	resp.getWriter().println(req.getParameter("name"));
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex){}
        
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            resp.getWriter().println(connection);
            connection.close();
        } catch(SQLException sqle){
            resp.getWriter().println("Cannot connect to DB.");
            resp.getWriter().println(sqle.getMessage());
            sqle.printStackTrace();
        }
    }
}
