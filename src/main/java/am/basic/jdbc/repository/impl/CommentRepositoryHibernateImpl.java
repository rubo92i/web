package am.basic.jdbc.repository.impl;

import am.basic.jdbc.model.*;
import am.basic.jdbc.repository.CommentRepository;
import am.basic.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;


public class CommentRepositoryHibernateImpl implements CommentRepository {


    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public void add(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
    }

    @Override
    public void update(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(comment);
        session.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(id);
        session.getTransaction().commit();
    }

    @Override
    public List<Comment> getByUserId(long userId) {
        NativeQuery<Comment> query = sessionFactory.openSession()
                .createNativeQuery("SELECT * FROM comment WHERE user_id = :nameik", Comment.class);
        query.setParameter("nameik", userId);
        return query.getResultList();
    }

    @Override
    public List<Comment> getAll() {
        NativeQuery<Comment> query = sessionFactory.openSession()
                .createNativeQuery("SELECT * FROM comment ", Comment.class);
        return query.getResultList();
    }

    @Override
    public void deleteByIdAndUserId(long commentId, long userId) {

    }
}
