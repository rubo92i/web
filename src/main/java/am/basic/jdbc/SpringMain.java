package am.basic.jdbc;

import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.ForbiddenException;
import am.basic.jdbc.model.excpetion.NotFoundException;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {


    public static void main(String[] args) throws NotFoundException, ForbiddenException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");

        UserRepository userRepository1 = (UserRepository) applicationContext.getBean("userRepository");

        System.out.println(userRepository == userRepository1);


        UserService userService = (UserService) applicationContext.getBean("userService");
        UserService userService1 = (UserService) applicationContext.getBean("userService");
        System.out.println(" userService == userService1 : " + (userService == userService1));

        UserService userService2 = (UserService) applicationContext.getBean("userService2");
        UserService userService3 = (UserService) applicationContext.getBean("userService2");
        System.out.println(" userService2 == userService3 : " + (userService2 == userService3));

        User user = userService.signIn("ruben.manukyan","password");


    }
}
