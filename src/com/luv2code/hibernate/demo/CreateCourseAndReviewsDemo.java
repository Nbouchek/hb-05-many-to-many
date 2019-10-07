package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            System.out.println("Starting transaction ... ");
            session.beginTransaction();

            // create course
            Course tempCourse = new Course("The game of life");

            // add some review
            tempCourse.addReview( new Review("I like it"));
            tempCourse.addReview( new Review("Good course"));
            tempCourse.addReview( new Review("Perfect"));
            tempCourse.addReview( new Review("Funny instructor"));

            // save the course ... and leverage the cascade all :-)
            System.out.println("Saving the course");
            System.out.println("\n\n" + tempCourse.getReviews() + "\n\n");
            System.out.println();
            session.save(tempCourse);

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
