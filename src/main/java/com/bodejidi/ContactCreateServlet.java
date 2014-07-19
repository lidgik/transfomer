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
        Contact contact = new Contact();
        contact.setName(req.getParameter("name"));
        contact.setMobile(req.getParameter("mobile"));
        contact.setVpmn(req.getParameter("vpmn"));
        contact.setEmail(req.getParameter("email"));
        contact.setHomeAddress(req.getParameter("homeAddress"));
        contact.setOfficeAddress(req.getParameter("officaAddress"));
        contact.setMemo(req.getParameter("memo"));
        contact.setJob(req.getParameter("job"));
        contact.setJobLevel(Integer.parseInt(req.getParameter("jobLevel")));
        resp.getWriter().println("Creat contact :"+contact);
    }
    
}
