package am.basic.jdbc.service.impl;

import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.DuplicateDataException;
import am.basic.jdbc.model.excpetion.NotFoundException;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.service.UserService;


public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User signIn(String username, String password) throws NotFoundException {
        User user = userRepository.getByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new NotFoundException("Wrong username or password");
        }
        return user;
    }

    @Override
    public void signUp(User user) throws DuplicateDataException {
        User duplicate = userRepository.getByUsername(user.getUsername());
        if (duplicate != null) {
            throw new DuplicateDataException("User with such username already exists");
        }
        userRepository.add(user);
    }

}
