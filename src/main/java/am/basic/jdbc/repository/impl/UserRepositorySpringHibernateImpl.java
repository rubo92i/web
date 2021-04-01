package am.basic.jdbc.repository.impl;

import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.UserRepository;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Data
@Repository
@Transactional(transactionManager = "txManager")
public class UserRepositorySpringHibernateImpl implements UserRepository {


    @Autowired
    private SessionFactory sessionFactory;


    private String name;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);

    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(id);
    }

    @Override
    public User getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }


    @Override
    public Optional<User> getByUsername(String username) {
        NativeQuery<User> query = sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM user WHERE username = :nameik", User.class);
        query.setParameter("nameik", username);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<User> getAll() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT  * FROM user", User.class)
                .getResultList();
    }
}
