package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesForNacerDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // start a transaction
            System.out.println("Starting transaction ... ");
            session.beginTransaction();

            // get student Nacer from db
            int studentId = 4;
            Student tempStudent = session.get(Student.class, studentId);
            System.out.println("\n\nLoaded student: " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses() + "\n\n");

            // create more courses
            Course tempCourse1 = new Course("Springboot");
            Course tempCourse2 = new Course("Angular");
            Course tempCourse3 = new Course("React");
            Course tempCourse4 = new Course("Javascript");

            // add Nacer to those courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);
            tempCourse3.addStudent(tempStudent);
            tempCourse4.addStudent(tempStudent);

            // save the courses
            System.out.println("\n\nSaving the courses ...");
            session.save(tempCourse1);
            session.save(tempCourse2);
            session.save(tempCourse3);
            session.save(tempCourse4);

            // commit transaction
            System.out.println("Committing transaction ... ");
            session.getTransaction().commit();
            System.out.println("Done :)");
        } finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}
