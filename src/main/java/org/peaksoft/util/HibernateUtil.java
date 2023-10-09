package org.peaksoft.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.peaksoft.model.entities.Course;
import org.peaksoft.model.entities.Instructor;
import org.peaksoft.model.entities.Student;
import org.peaksoft.model.entities.StudentIdCard;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(StudentIdCard.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        return sessionFactory;
    }
}
