package am.basic.jdbc.service.impl;

import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.DuplicateDataException;
import am.basic.jdbc.model.excpetion.ForbiddenException;
import am.basic.jdbc.model.excpetion.NotFoundException;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.service.UserService;
import am.basic.jdbc.util.CustomMailSender;
import am.basic.jdbc.util.Generator;
import am.basic.jdbc.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Primary
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    // Automatic bean injection
    // 1. type , searching bean with same type
    // 2. name , if there is more then one bean with same type, it checks name
    // 3. Qualifier . Using for change bean default name which is class name with lowerCase and injected bean name which is field name by default
    // 4. @Primary annotation, which help to choose right bean in ambiguous cases


    private final UserRepository userRepository;

    private final CustomMailSender customMailSender;

    @Override
    @Transactional(rollbackFor = Throwable.class,   // for which exception rollback transaction , default type is RuntimeException
            noRollbackFor = NotFoundException.class, //  for which exception do not rollback transaction
            transactionManager = "transactionManager", // transaction manager bean name, default name is  transactionManager
            isolation = Isolation.READ_COMMITTED // isolation lvl configuration
    )
    public User signIn(String username, String password) throws NotFoundException, ForbiddenException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wrong username or password"));
        NotFoundException.check(!user.getPassword().equals(password), "Wrong username or password");
        ForbiddenException.check(user.getStatus() == Status.UNVERIFIED, "Please verify");
        return user;
    }


    // @Override
    public User signInFunctional(String username, String password) throws NotFoundException, ForbiddenException {
        return Optional.ofNullable(
                userRepository.getByUsername(username)
                        .filter(u -> u.getPassword().equals(password))
                        .orElseThrow(() -> new NotFoundException("Wrong username or password")))
                .filter(user -> user.getStatus() != Status.UNVERIFIED)
                .orElseThrow(() -> new ForbiddenException("Please verify"));

    }

    @Override
    public void signUp(User user) throws DuplicateDataException {
        DuplicateDataException.check(
                userRepository.getByUsername(user.getUsername()).isPresent(),
                "User with such username already exists");
        user.setStatus(Status.UNVERIFIED);
        user.setCode(Generator.getRandomDigits(5));
        userRepository.add(user);
        customMailSender.sendMail("Verification", "Your code is " + user.getCode(), user.getUsername());
    }

    @Override
    public void verify(String username, String code) throws NotFoundException, ForbiddenException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wrong username or password"));
        ForbiddenException.check(!code.equals(user.getCode()), "Wrong verification code");
        user.setStatus(Status.ACTIVE);
        user.setCode(null);
        userRepository.update(user);
    }

    @Override
    public void resend(String username) throws NotFoundException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wrong username or password"));
        user.setCode(Generator.getRandomDigits(5));
        userRepository.update(user);
        customMailSender.sendMail("Verification", "Your code is " + user.getCode(), user.getUsername());
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws ForbiddenException, NotFoundException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wrong username or password"));
        ForbiddenException.check(!user.getPassword().equals(oldPassword), "wrong password");
        user.setPassword(newPassword);
        userRepository.update(user);
    }

}
