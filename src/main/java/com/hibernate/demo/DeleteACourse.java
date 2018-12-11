package com.hibernate.demo;

import javax.swing.JOptionPane;

import com.demo.entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;;

public class DeleteACourse{

    public static void main(String[] args){
        // Bulid a factory and use the Course as the annotated class

        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();

        // start a new session

        Session session = factory.getCurrentSession();

        // try getting an object from the database

        int id = 13;
        try{
            // start a new transaction
            session.beginTransaction();
            
            // get the course to delete            
            Course courseToDelete = session.get(Course.class, id);

            System.out.println(String.format("Deleting the course %s:", courseToDelete.getTitle()));
            // delete the course
            session.delete(courseToDelete);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println(String.format("The course %s has been successfuly deleted", courseToDelete.getTitle()));

        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, String.format("The course of id %s that you are trying to delete does not exist", id),"Course Not Found",1);
        }
        finally{
            session.close();
            factory.close();
        }


    }

}