package com.bodejidi;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ContactServlet extends HttpServlet{
    String sql = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
        if ((req.getParameter("name") == null) || (req.getParameter("name") == "")){
            sql = "select * from contact";
            resp.getWriter().println("get all contacts!");
            
            for(Map map: getAllContacts(sql)){
                Map contact = map;
            
                resp.getWriter().println("Id:" + contact.get("id"));
                resp.getWriter().println("Name:" + contact.get("name"));
                resp.getWriter().println("Mobile:" + contact.get("mobile"));
                resp.getWriter().println("Vpmn:" + contact.get("vpmn"));
                resp.getWriter().println("Job:" + contact.get("job"));
            }
        }else{
            sql = "select * from contact where name ='" + req.getParameter("name") + "'";
            resp.getWriter().println(req.getParameter("name"));
            Map contact = getContactByName(sql);
            
            if(contact.get("name") != null){
                resp.getWriter().println("Name: " + contact.get("name"));
                resp.getWriter().println("Mobile: " + contact.get("mobile"));
                resp.getWriter().println("Vpmn: " + contact.get("vpmn"));
                resp.getWriter().println("Email: " + contact.get("email"));
                resp.getWriter().println("HomeAddress: " + contact.get("homeAddress"));
                resp.getWriter().println("OfficeAddress: " + contact.get("officeAddress"));
                resp.getWriter().println("Memo: " + contact.get("memo"));
                resp.getWriter().println("Job: " + contact.get("job"));
                resp.getWriter().println("JobLevel: " + contact.get("jobLevel"));
            }else{
                resp.getWriter().println("Contact not found!");
            }
        }
    }
    
    private List<Map> getAllContacts(String sql){
        List<Map> contacts = new ArrayList();
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
            
            while (rs.next()){
                Map contact = new HashMap();
                
                contact.put("id", rs.getInt("id"));
                contact.put("name", rs.getString("name"));
                contact.put("mobile", rs.getString("mobile"));
                contact.put("vpmn", rs.getString("vpmn"));
                contact.put("job", rs.getString("job"));
                
                contacts.add(contact);
            }
      
        } catch(SQLException sqle){
            sqle.printStackTrace();
        }
        
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
        return contacts;
    }
    
    private Map getContactByName(String sql){
        Map contact = new HashMap(); 
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
            
            if (rs.next()){
                contact.put("name", rs.getString("name"));
                contact.put("mobile", rs.getString("mobile"));
                contact.put("vpmn", rs.getString("vpmn"));
                contact.put("email", rs.getString("email"));
                contact.put("homeAddress", rs.getString("home_address"));
                contact.put("officeAddress", rs.getString("office_address"));
                contact.put("memo", rs.getString("memo"));
                contact.put("job", rs.getString("job"));
                contact.put("jobLevel", rs.getString("job_level"));
            }
            
        } catch(SQLException sqle){
            sqle.printStackTrace();
        }
        
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
        return contact;
    }
}
