package com.bodejidi;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 	throws IOException, ServletException{
    
    	resp.getWriter().println(req.getParameter("name"));
        
    }
}
