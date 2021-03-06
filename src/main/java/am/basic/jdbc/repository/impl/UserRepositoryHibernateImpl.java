package am.basic.jdbc.repository.impl;

import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.util.HibernateUtil;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Data
@Repository
public class UserRepositoryHibernateImpl implements UserRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    private String name;

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
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
    public User getById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(User.class, id);
    }


    @Override
    public Optional<User> getByUsername(String username) {
        NativeQuery<User> query = sessionFactory.openSession()
                .createNativeQuery("SELECT * FROM user WHERE username = :nameik", User.class);
        query.setParameter("nameik", username);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<User> getAll() {
        return sessionFactory
                .openSession()
                .createNativeQuery("SELECT  * FROM user", User.class)
                .getResultList();
    }
}
