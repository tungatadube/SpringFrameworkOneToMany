package com.hibernate.demo;

import java.util.List;

import com.demo.entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QuerryInstructorFromDatabaseAndGiveCourse{

    public static void main(String[] args){

    // Build the Factory

    SessionFactory factory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .buildSessionFactory();

    // create a session to use
    Session session = factory.getCurrentSession();

    int id = 8;
    try{
        // begin a transaction to retrieve an object fronm the database
        session.beginTransaction();

        // get an Instructor object from the database

        // Instructor thisSessionsInstructor = session.get(Instructor.class, id);
        
        // // Instatiate a course Object and bind the Instructor to the course
        // Course newCourse = new Course("Fluid DynamicsII", thisSessionsInstructor);
        // // save the transaction
        // session.save(newCourse);
        List<Course> fluidDynamics = (List<Course>) (session.createQuery("from com.demo.entity.Course s where s.title like '%Fluid Dyn%'").list());
        for (Course var : fluidDynamics) {
            System.out.println(var);
            System.out.println(var.getInstructor());
            System.out.println(var.getInstructor().getInstructorDetail());

        }
        // commit the change on to the database
        session.getTransaction().commit();

    


    }
    finally{
        session.close();
        factory.close();
    }
}
}
