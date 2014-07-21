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

public class GroupServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException{
		resp.getWriter().println("Group servlet!");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) {}

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&&password=");
			resp.getWriter().println(conn);
			conn.close();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}    
}
