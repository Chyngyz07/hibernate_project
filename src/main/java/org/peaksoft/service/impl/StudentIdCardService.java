package org.peaksoft.service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.peaksoft.model.entities.StudentIdCard;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.util.List;

public class StudentIdCardService implements Service<StudentIdCard> {

    @Override
    public void create(StudentIdCard studentIdCard) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(studentIdCard);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Long id,StudentIdCard studentIdCard) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.get(StudentIdCard.class,id);
            studentIdCard.setCreatDate(studentIdCard.getCreatDate());
            studentIdCard.setIdentityNumber(studentIdCard.getIdentityNumber());
            session.persist(studentIdCard);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<StudentIdCard> getAll() {
        List<StudentIdCard> studentIdCards = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            studentIdCards = session.createQuery("FROM StudentIdCard ", StudentIdCard.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return studentIdCards;
    }

    @Override
    public StudentIdCard getById(Long id) {
        StudentIdCard studentIdCard = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            studentIdCard = session.get(StudentIdCard.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return studentIdCard;
    }

    @Override
    public String deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(session.get(StudentIdCard.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return getAll().toString();
    }
}
