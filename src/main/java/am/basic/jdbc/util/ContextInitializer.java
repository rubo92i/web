package am.basic.jdbc.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextInitializer implements ServletContextListener {


    public static ApplicationContext applicationContext;


    public void contextInitialized(ServletContextEvent sce) {
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
    }
}
