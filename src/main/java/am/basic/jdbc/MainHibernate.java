package am.basic.jdbc;

import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.repository.impl.UserRepositoryHibernateImpl;

public class MainHibernate {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryHibernateImpl();

        User user = userRepository.getById(1);
        System.out.println(user);

        User user1 = userRepository.getByUsername("ruben.manukyan");

        System.out.println(user1);
    }
}
