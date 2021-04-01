package am.basic.jdbc.repository.impl;

import am.basic.jdbc.model.Comment;
import am.basic.jdbc.repository.CommentRepository;
import am.basic.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


@Lazy(false) // this is for configuring bean creation
@Repository
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // configuring bean scope singleton or prototype
public class CommentRepositoryHibernateImpl implements CommentRepository {


    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @PostConstruct // methods which are annotated with this annotation will be invoked by spring framework after bean is created
    public void init(){
        System.out.println("init");
    }


    @PreDestroy // methods which are annotated with this annotation will be invoked by spring framework before bean is destroyed
    public void destroy(){
        System.out.println("init");
    }

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
