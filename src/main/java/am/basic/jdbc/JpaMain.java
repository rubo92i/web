package am.basic.jdbc;

import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.UserRepositoryJpa;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Optional;

public class JpaMain {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");

        UserRepositoryJpa userRepositoryJpa = applicationContext.getBean(UserRepositoryJpa.class);

//        Optional<User> user = userRepositoryJpa.findById(1L);
//
//
//
//        if (user.isPresent()){
//            System.out.println(user.get());
//        }

        Optional<User> test = userRepositoryJpa.getByUsername("ruben.manukyan");


        test.ifPresent(System.out::println);



        User user = userRepositoryJpa.getByUsernameAndCodeIn("ruben.manukyan", Arrays.asList("123456","1596256","51561561"));

        System.out.println(user);
    }
}
