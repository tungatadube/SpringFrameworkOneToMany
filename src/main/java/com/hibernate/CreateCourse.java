package com.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.entity.Instructor;
import com.demo.entity.InstructorDetail;
import com.demo.entity.Course;

public class CreateCourse{

    public static void main (String[] args){

        // build the factory

        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();

        // start a new session using this created factory

        Session session = factory.getCurrentSession();

        //try to build and  persist the objects using this newly created session

        try{

            // start a new transaction using the current session
            session.beginTransaction();

            // instatiate an instructor to give a course
            Instructor thisInstructor = new Instructor("zimwara", "Daniel", "dzimwara@nust.ac.zw");
            InstructorDetail thisInstructorsDetails = new InstructorDetail("http://www.youtube.com/zimwara", "Loves to teach");

            // associate these two objects
            thisInstructor.setInstructorDetail(thisInstructorsDetails);

            session.save(thisInstructor);            
            // instantiate a course to bind to the instructor that has instructor details bound to it            
            Course myNewCourse = new Course("Design Engineering", thisInstructor);

            // save your changes
            session.save(myNewCourse);
            // commite these changes

            session.getTransaction().commit();

        }
        finally{
            session.close();
            factory.close();
        }
    }
}