package am.basic.jdbc.repository.impl;

import am.basic.jdbc.model.Role;
import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.RoleRepository;
import am.basic.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository
public class RoleRepositoryHibernateImpl implements RoleRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();



    @Override
    public Role getById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(Role.class, id);
    }



}
