package am.basic.jdbc.aold;

import am.basic.jdbc.model.Role;
import am.basic.jdbc.model.excpetion.ForbiddenException;
import am.basic.jdbc.model.excpetion.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpringMainOld {


    public static void main(String[] args) throws NotFoundException, ForbiddenException {

        Role role = new Role()
                .withName("ROLE_ADMIN")
                .withId(1);



         String s6 = new StringBuilder()
                 .append("Hello")
                 .append("world")
                 .append("Hello")
                 .append("Armenia")
                 .append("test")
                 .reverse()
                 .toString();
        System.out.println(s6);

        String s2 = null;

        Optional<String> optional = Optional.ofNullable(s2)
                .filter(s1 -> s1.length()> 5);




        List<String> strings = Arrays.asList("Hello", "World", "sdfsdf", "sdfsddfs");

        boolean anyMatch = strings.stream()
                .anyMatch(s -> s.length() == 7);

         String sevenCharacterString = strings.stream()
                        .filter(s -> s.length() == 7)
                        .findFirst()
                        .orElseThrow(()->new NotFoundException("There is no string with 7 simbols"));




        long sum = strings.stream()
                .mapToInt(String::length)
                .sum();

        List<String> sample = strings.stream()
                .filter(s -> s.length() > 4)
                .map(s -> s.substring(0, 2))
                .collect(Collectors.toList());


        List<Integer> lengths = strings.stream()
                .map(String::length)
                .collect(Collectors.toList());

        List<String> strings1 = strings.stream()
                .map(s -> s.substring(0, 2))
                .collect(Collectors.toList());


//        User user = new User();
//        user.setName("Ruben");
//
//
//        user.setRoles(Collections.singletonList(new Role("Name")));
//
//
//
//
//        Set<String> stringSet =  new HashSet<>(Arrays.asList("Hello","World","sdfsdf","sdfsddfs"));
//
//
//
//
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
//        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
//
//        UserRepository userRepository1 = (UserRepository) applicationContext.getBean("userRepository");
//
//        System.out.println(userRepository == userRepository1);
//
//
//        UserService userService = (UserService) applicationContext.getBean("userService");
//        UserService userService1 = (UserService) applicationContext.getBean("userService");
//        System.out.println(" userService == userService1 : " + (userService == userService1));
//
//        UserService userService2 = (UserService) applicationContext.getBean("userService2");
//        UserService userService3 = (UserService) applicationContext.getBean("userService2");
//        System.out.println(" userService2 == userService3 : " + (userService2 == userService3));
//
//        User user1 = userService.signIn("ruben.manukyan","password");


    }
}
