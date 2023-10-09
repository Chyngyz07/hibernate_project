package org.peaksoft;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.peaksoft.model.entities.Course;
import org.peaksoft.model.entities.Instructor;
import org.peaksoft.model.entities.Student;
import org.peaksoft.model.entities.StudentIdCard;
import org.peaksoft.model.enums.Gender;
import org.peaksoft.model.enums.StudyFormat;
import org.peaksoft.service.impl.CourseService;
import org.peaksoft.service.impl.InstructorService;
import org.peaksoft.service.impl.StudentIdCardService;
import org.peaksoft.service.impl.StudentService;
import org.peaksoft.util.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Java 7", "Month 9", LocalDate.of(2023, 6, 1)));
//        CourseService courseService = new CourseService();
//        courseService.create(course);


        Instructor instructor = new Instructor("Tom", "Hardy", 25, Gender.MALE, LocalDate.of(2023, 9, 5));
//        InstructorService instructorService = new InstructorService();
//        instructorService.create(instructor);
//        instructor.setCourse(course);
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            session.persist(instructor);
//            session.getTransaction().commit();
//            session.close();
//        }catch (HibernateException e){
//            System.out.println(e.getMessage());
//        }



//        StudentIdCardService studentIdCardService = new StudentIdCardService();
//        StudentIdCard studentIdCard = new StudentIdCard("1111", LocalDate.of(2023, 8, 12));
//        studentIdCardService.create(studentIdCard);


        Student student = new Student("Chyngyz", 24, StudyFormat.ONLINE, Gender.MALE, LocalDate.of(2023, 10, 7));
//        StudentService service = new StudentService();
//        service.create(student);
//        studentIdCard.setStudent(student);
//        student.setStudentIdCard(studentIdCard);
        student.setCourses(courses);

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
            session.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }


    }
}
