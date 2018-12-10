package com.hibernate.demo;

import javax.swing.JOptionPane;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.demo.entity.InstructorDetail;


public class GetInstructorDetailDemo
{
	public static void main(String[] args)
	{
		// create the session factory

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();

		//create a session
		Session session = factory.getCurrentSession();

		// wrap the persistence around  a try finally block `

		int id = 16;
		try {
			//start a new session

			session.beginTransaction();
			// get the instructor detail object
			InstructorDetail thisInstructorDetail = session.get(InstructorDetail.class, id);			
			// print the instructor detail
			System.out.println(String.format("InstructorDetail: %s", thisInstructorDetail));
						// print the associated Instructor
			System.out.println(String.format("Instructor: %s ", thisInstructorDetail.getInstructor().toString()));
			thisInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(thisInstructorDetail);
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		catch(NullPointerException n){
			JOptionPane.showMessageDialog(null, String.format("The Details of the instructor of id %d that you are trying to view do not	exist", id), "Not Found", 1);

		}
		finally {
			factory.close();
		}


	}
}
