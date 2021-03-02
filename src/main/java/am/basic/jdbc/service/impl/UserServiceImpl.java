package am.basic.jdbc.service.impl;

import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.DuplicateDataException;
import am.basic.jdbc.model.excpetion.ForbiddenException;
import am.basic.jdbc.model.excpetion.NotFoundException;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.service.UserService;
import am.basic.jdbc.util.Generator;
import am.basic.jdbc.util.Status;


public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User signIn(String username, String password) throws NotFoundException, ForbiddenException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null || !user.getPassword().equals(password), "Wrong username or password");
        ForbiddenException.check(user.getStatus() == Status.UNVERIFIED,"Please verify");
        return user;
    }

    @Override
    public void signUp(User user) throws DuplicateDataException {
        User duplicate = userRepository.getByUsername(user.getUsername());
        DuplicateDataException.check(duplicate != null, "User with such username already exists");
        user.setStatus(Status.UNVERIFIED);
        user.setCode(Generator.getRandomDigits(5));
        userRepository.add(user);
    }

    @Override
    public void verify(String username, String code) throws NotFoundException, ForbiddenException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null , "Wrong username or password");
        ForbiddenException.check(!code.equals(user.getCode()),"Wrong verification code");
        user.setStatus(Status.ACTIVE);
        user.setCode(null);
        userRepository.update(user);
    }

    @Override
    public void resend(String username) throws NotFoundException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null , "Wrong username or password");
        user.setCode(Generator.getRandomDigits(5));
        userRepository.update(user);
    }

}
