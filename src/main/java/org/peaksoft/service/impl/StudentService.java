package org.peaksoft.service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.peaksoft.model.entities.Student;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.util.List;

public class StudentService  implements Service<Student> {
    @Override
    public void create(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
        } catch (SessionException s) {
            System.out.println(s.getMessage());
        }

    }

    @Override
    public void update(Long id,Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.get(Student.class,id);
            student.setName(student.getName());
            student.setAge(student.getAge());
            student.setGender(student.getGender());
            student.setStudyFormat(student.getStudyFormat());
            student.setCreatDate(student.getCreatDate());
            session.persist(student);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Student> getAll() {
        List<Student> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            students = session.createQuery("FROM Student", Student.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    @Override
    public Student getById(Long id) {
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            student = session.get(Student.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public String deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(session.get(Student.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return getAll().toString();
    }
}
