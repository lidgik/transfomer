package com.bodejidi;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class GroupServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException{
		String sql = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		if(((req.getParameter("department_name") == null) || (req.getParameter("department_name") == ""))) {
			resp.getWriter().println("All Department!");
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch(Exception e) {}

			try {
				sql = "select * from department";
				conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
				stmt = conn.createStatement();
				resp.getWriter().println("stmt!");
				rs = stmt.executeQuery(sql);
				resp.getWriter().println("rs!");

				rs.next();
				resp.getWriter().println(rs.getString("department_name"));
			} catch(SQLException sqle) {
				resp.getWriter().println("cannot connect to db.");
				sqle.printStackTrace();
			}

			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){}
			}

			if(stmt != null) {
				try {
					stmt.close();
				} catch(Exception e){}
			}

			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e){}
			}
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch(Exception e) {}

			try {
				sql = "select * from department where department_name='" + req.getParameter("department_name") + "'";
				conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
				stmt = conn.createStatement();
				resp.getWriter().println(req.getParameter("department_name"));
				resp.getWriter().println("stmt!");
				rs = stmt.executeQuery(sql);
				resp.getWriter().println("rs!");

				if(rs.next()){
					resp.getWriter().println(rs.getString("department_memo"));
				} else {
					resp.getWriter().println("no such department!");
				}
			} catch(SQLException sqle) {
				resp.getWriter().println("cannot connect to db.");
				sqle.printStackTrace();
			}

			if(rs != null) {
				try {
					rs.close();
				} catch(Exception e){}
			}

			if(stmt != null) {
				try {
					stmt.close();
				} catch(Exception e){}
			}

			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e){}
			}
		}
	}    
}
