package com.hibernate;

import com.demo.entity.Instructor;
import com.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.demo.entity.Course;

public class CreateInstructorDemo {
public static void main (String[] args){
    SessionFactory factory = new Configuration()
                            .configure()
                            .addAnnotatedClass(Instructor.class)
                            .addAnnotatedClass(InstructorDetail.class)
                            .addAnnotatedClass(Course.class)
                            .buildSessionFactory();

    // create a session

    Session session = factory.getCurrentSession();

    try{
        //create the objects

        Instructor thisInstructor = new Instructor("Emmanuel", "Adibao", "fbathez@francesoccer.fr");
        InstructorDetail thisInstructorDetails = new InstructorDetail("http://www.youtube.com/Adibao", "Loves to play jog");

        // Associate the objects
        thisInstructor.setInstructorDetail(thisInstructorDetails);

        //begin persisting the objects
        session.beginTransaction();
        //save the objects
        session.save(thisInstructor);
        //commit the changes
        session.getTransaction().commit();
        //print something on to the console
        System.out.println("Done!");

    



    }
    finally{
        session.close();
        factory.close();
    }
                            

    }
}