package org.peaksoft.service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.peaksoft.model.entities.Course;
import org.peaksoft.model.entities.Student;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.util.List;

public class CourseService implements Service<Course> {
    @Override
    public void create(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Long id,Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(session.get(Course.class,id));
            course.setCourseName(course.getCourseName());
            course.setDurationMonth(course.getDurationMonth());
            course.setCreateDate(course.getCreateDate());
            session.persist(course);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            courses = session.createQuery("FROM Course ", Course.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }

    @Override
    public Course getById(Long id) {
        Course course = new Course();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            course = session.get(Course.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public String deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(session.get(Course.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return getAll().toString();
    }
}
