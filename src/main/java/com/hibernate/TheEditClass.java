package com.hibernate;

import com.demo.entity.Course;
import com.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.entity.Instructor;

public class TheEditClass{

    public static void main(String[] args){

        
            // build the factory
            SessionFactory factory = new Configuration()
                                    .configure()
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .buildSessionFactory();
    
    
            // create a new session
            Session session = factory.getCurrentSession();
    
            // now that you have a session build the object to use in the session
    
            int id = 2;
            try{            
    
            // begin a transaction
            session.beginTransaction();

            Instructor thisInstructor = session.get(Instructor.class, id);
    
            // Updat the fiels that are required of this object
    
            System.out.println(String.format("The current email for this instructor is : %s", thisInstructor.getEmail()));
            thisInstructor.setEmail("eadebao@nigeriansoccer.co.ng");
            System.out.println(String.format("The current email for this instructor is now : %s", thisInstructor.getEmail()));
    
            // save the changes to te object
            session.save(thisInstructor);
    
            // commit the transaction
            session.getTransaction().commit();
        }
        finally{
            // close the Session to minimise resources leak
            session.close();
            // close the factory to minimise resources leak
            factory.close();
        
        }



    }

    
}