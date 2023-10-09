package org.peaksoft.service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.peaksoft.model.entities.Instructor;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.util.List;

public class InstructorService implements Service<Instructor> {
    @Override
    public void create(Instructor instructor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(instructor);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Long id, Instructor instructor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.get(Instructor.class,id);
            instructor.setName(instructor.getName());
            instructor.setLastName(instructor.getLastName());
            instructor.setAge(instructor.getAge());
            instructor.setGender(instructor.getGender());
            session.persist(instructor);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }
    @Override
    public List<Instructor> getAll() {
        List<Instructor> instructors = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            instructors = session.createQuery("FROM Instructor ", Instructor.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return instructors;
    }

    @Override
    public Instructor getById(Long id) {
        Instructor instructor = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            instructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return instructor;
    }

    @Override
    public String deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(session.get(Instructor.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return getAll().toString();
    }
}
