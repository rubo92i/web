package am.basic.jdbc.config;


import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.service.UserService;
import am.basic.jdbc.service.impl.UserServiceImpl;
import am.basic.jdbc.util.CustomMailSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeansConfig {


    @Bean
    @Qualifier
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // configuring bean scope singleton or prototype
    public UserService userService(UserRepository userRepository, CustomMailSender customMailSender){
        return new UserServiceImpl(userRepository,customMailSender);
    }
}
