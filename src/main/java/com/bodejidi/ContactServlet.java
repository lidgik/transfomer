package com.bodejidi;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

public class ContactServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
        ContactService contactService = new ContactService();
    
        if ((req.getParameter("name") == null) || (req.getParameter("name") == "")){
            req.setAttribute("contactList", contactService.getAllContacts());
            
            getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/contact/list.jsp")
                .forward(req, resp);
        }else{
            resp.getWriter().println(req.getParameter("name"));  
            List<Contact> contacts = contactService.getContactByName(req.getParameter("name")); 
            if(contacts.size() > 0){
                req.setAttribute("contact", contactService.getContactsByName());
                
                getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/contact/show.jsp")
                    .forward(req, resp);
                    
            }else{
                resp.getWriter().println("Contact not found!");
            }
        }
    }
    
}
