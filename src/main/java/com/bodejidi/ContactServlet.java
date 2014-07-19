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

public class ContactServlet extends HttpServlet{
    String sql = null;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
        if ((req.getParameter("name") == null) || (req.getParameter("name") == "")){
            resp.getWriter().println("get all contacts!");            
            for(Contact contact: getAllContacts()){
                resp.getWriter().println("Id:" + contact.getId());
                resp.getWriter().println("Name:" + contact.getName());
                resp.getWriter().println("Mobile:" + contact.getMobile());
                resp.getWriter().println("Vpmn:" + contact.getVpmn());
                resp.getWriter().println("Job:" + contact.getJob());
            }
        }else{
            resp.getWriter().println(req.getParameter("name"));
            Contact contact = getContactByName(req.getParameter("name"));            
            if(contact != null){
                resp.getWriter().println("Id: " + contact.getId());
                resp.getWriter().println("Name: " + contact.getName());
                resp.getWriter().println("Mobile: " + contact.getMobile());
                resp.getWriter().println("Vpmn: " + contact.getVpmn());
                resp.getWriter().println("Email: " + contact.getEmail());
                resp.getWriter().println("HomeAddress: " + contact.getHomeAddress());
                resp.getWriter().println("OfficeAddress: " + contact.getOfficeAddress());
                resp.getWriter().println("Memo: " + contact.getMemo());
                resp.getWriter().println("Job: " + contact.getJob());
                resp.getWriter().println("JobLevel: " + contact.getJobLevel());
            }else{
                resp.getWriter().println("Contact not found!");
            }
        }
    }
    
    private List<Contact> getAllContacts(){
        return findAllContactsBySQL("select * from contact");
    }
        
    private List<Contact> findAllContactsBySQL(String sql){ 
        List<Contact> contacts = new ArrayList();
        DatabaseManager db = new DatabaseManager();
        db.createDatebaseConnectionAndExecute(sql);
        
        try{
            if(db.rs != null){
                while (db.rs.next()){
                    contacts.add(getContactFromResultSet(db.rs));
                }
            }
        } catch(SQLException sqle){
            sqle.printStackTrace();
        } finally{
            db.close();
        }
        return contacts;
    }
    
    private Contact getContactByName(String name){
        return findContactByName("select * from contact where name ='" + name + "'");
    }
    
    private Contact findContactByName(String sql){
        Contact contact = null; 
        DatabaseManager db = new DatabaseManager();
        db.createDatebaseConnectionAndExecute(sql);
        
        try{
            if (db.rs.next()){
                contact = getContactFromResultSet(db.rs);
            }
        } catch(SQLException sqle){
            sqle.printStackTrace();
        } finally{
            db.close();
        }
        return contact;
    }
    
    public Contact getContactFromResultSet(ResultSet rs)
        throws SQLException{
        Contact contact = new Contact();
        
        contact.setId(rs.getInt("id"));
        contact.setName(rs.getString("name"));
        contact.setMobile(rs.getString("mobile"));
        contact.setVpmn(rs.getString("vpmn"));
        contact.setEmail(rs.getString("email"));
        contact.setHomeAddress(rs.getString("home_address"));
        contact.setOfficeAddress(rs.getString("office_address"));
        contact.setMemo(rs.getString("memo"));
        contact.setJob(rs.getString("job"));
        contact.setJobLevel(rs.getInt("job_level"));
        
        return contact;
    }
}

class DatabaseManager{
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
