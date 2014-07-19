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
                for(Contact contact: contacts){
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
                }
            }else{
                resp.getWriter().println("Contact not found!");
            }
        }
    }
    
}
