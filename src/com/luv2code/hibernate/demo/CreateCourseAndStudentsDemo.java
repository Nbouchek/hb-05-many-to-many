package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
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

            // create course
            Course tempCourse = new Course("Four in a row");
            // save the course
            System.out.println("\n\nSaving course: " + tempCourse);
            session.save(tempCourse);
            System.out.println("\n\nCompleted saving course: " + tempCourse + "\n\n");

            // create the students
            Student tempStudent1 = new Student("Adam", "Bouchekhima", "adam@luv2code.com");
            Student tempStudent2 = new Student("Hana", "Bouchekhima", "hana@luv2code.com");
            Student tempStudent3 = new Student("Linda", "Bouchekhima", "linda@luv2code.com");
            Student tempStudent4 = new Student("Nacer", "Bouchekhima", "nacer@luv2code.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);
            tempCourse.addStudent(tempStudent3);
            tempCourse.addStudent(tempStudent4);

            // save the students
            System.out.println("\n\nSaving Students ...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            session.save(tempStudent4);

            System.out.println("\n\nSaved students: "  + tempCourse.getStudents() + "\n\n");
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
