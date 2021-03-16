package am.basic.jdbc;

import am.basic.jdbc.model.Card;
import am.basic.jdbc.model.Role;
import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.RoleRepository;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.repository.impl.RoleRepositoryHibernateImpl;
import am.basic.jdbc.repository.impl.UserRepositoryHibernateImpl;

import java.util.ArrayList;
import java.util.List;

public class MainHibernate {

//    public static void main(String[] args) {
////        UserRepository userRepository = new UserRepositoryHibernateImpl();
////
////        User user = userRepository.getById(1);
////        Card card = new Card();
////        card.setNumber("12231654564");
////        card.setCvc("987");
////        card.setExpiration("07/28");
////
////
////        user.setCard(card);
////
////        userRepository.update(user);
////
////
////     }


    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryHibernateImpl();
        User user = userRepository.getById(1);
        System.out.println(user);
    }

}
