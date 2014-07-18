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

public class ContactServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
        String sql = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
            	
        if ((req.getParameter("name") == null) || (req.getParameter("name") == "")){
            sql = "select * from contact";
            resp.getWriter().println("get all contacts!");
          
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
                    resp.getWriter().println(rs.getInt("id"));
                    resp.getWriter().println(rs.getString("name"));
                    resp.getWriter().println(rs.getString("mobile"));
                    resp.getWriter().println(rs.getString("office_address"));
                }
          
            } catch(SQLException sqle){
                resp.getWriter().println("Cannot connect to DB.");
                resp.getWriter().println(sqle.getMessage());
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
        }else{
            sql = "select * from contact where name ='" + req.getParameter("name") + "'";
            resp.getWriter().println(req.getParameter("name"));
            
            String name = null;
            String mobile = null;
            String vpmn = null;
            String email = null;
            String homeAddress = null;
            String officeAddress = null;
            String memo = null;
            String job = null;
            Integer jobLevel = null;
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
                
                    name = rs.getString("name");
                    mobile = rs.getString("mobile");
                    vpmn = rs.getString("vpmn");
                    email = rs.getString("email");
                    homeAddress = rs.getString("home_address");
                    officeAddress = rs.getString("office_address");
                    memo = rs.getString("memo");
                    job = rs.getString("job");
                    jobLevel = rs.getInt("job_level");
                    
                    resp.getWriter().println("Name: " + name);
                    resp.getWriter().println("Mobile: " + mobile);
                    resp.getWriter().println("Vpmn: " + vpmn);
                    resp.getWriter().println("Email: " + email);
                    resp.getWriter().println("HomeAddress: " + homeAddress);
                    resp.getWriter().println("OfficeAddress: " + officeAddress);
                    resp.getWriter().println("Memo: " + memo);
                    resp.getWriter().println("Job: " + job);
                    resp.getWriter().println("JobLevel: " + jobLevel);
                    
                   
                }
                else{
                    resp.getWriter().println("no such contact!");
                }
                
            } catch(SQLException sqle){
                resp.getWriter().println("Cannot connect to DB.");
                resp.getWriter().println(sqle.getMessage());
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
        }
    }
}
