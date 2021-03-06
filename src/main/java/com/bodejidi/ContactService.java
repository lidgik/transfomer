package com.bodejidi;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

public class ContactService {
    
    String sql = null;

    public List<Contact> getAllContacts(){
        return findContactBySQL("select * from contact");
    }
        
    public List<Contact> findContactBySQL(String sql){ 
        List<Contact> contacts = new ArrayList();
        DatabaseManager db = new DatabaseManager();
        db.createDatebaseConnectionAndExecute(sql);
        
        try{
            while (db.rs.next()){
                contacts.add(getContactFromResultSet(db.rs));
            }
        } catch(SQLException sqle){
            sqle.printStackTrace();
        } finally{
            db.close();
        }
        return contacts;
    }
    
    public List<Contact> getContactByName(String name){
        return  findContactBySQL("select * from contact where name ='" + name + "'");
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
    
    public Contact save(Contact contact){
        String sql = "INSERT INTO `contact` (`id`, `name`, `mobile`, "
            + "`vpmn`, `email`, `home_address`, `office_address`, `memo`, "
            + "`job`, `job_level`) "
            + "VALUES"
            + "(null,'"
            + contact.getName() + "','"
            + contact.getMobile() + "','"
            + contact.getVpmn() + "','"
            + contact.getEmail() + "','"
            + contact.getHomeAddress() + "','"
            + contact.getOfficeAddress() + "','"
            + contact.getMemo() + "','"
            + contact.getJob() + "',"
            + contact.getJobLevel() + ")";
        DatabaseManager db = new DatabaseManager();
        db.executeUpdate(sql);
        
        try {
        if(db.rs != null) {
            db.rs.next();
            contact.setId(db.rs.getInt(1));
        }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        db.close();

        return contact;
    }
    
}
