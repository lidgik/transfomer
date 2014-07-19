package com.bodejidi;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

public class ContactCreateServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
        getServletContext()
              .getRequestDispatcher("/WEB-INF/jsp/contact/create.jsp")
              .forward(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
            resp.getWriter().println("Name: " + req.getParameter("name"));
            resp.getWriter().println("Mobile: " + req.getParameter("mobile"));
            resp.getWriter().println("Vpmn: " + req.getParameter("vpmn"));
            resp.getWriter().println("Email: " + req.getParameter("email"));
            resp.getWriter().println("HomeAddress: " + req.getParameter("homeAddress"));
            resp.getWriter().println("OfficeAddress: " + req.getParameter("officaAddress"));
            resp.getWriter().println("Memo: " + req.getParameter("memo"));
            resp.getWriter().println("Job: " + req.getParameter("job"));
            resp.getWriter().println("JobLevel: " + req.getParameter("jobLevel"));
    }
    
}
