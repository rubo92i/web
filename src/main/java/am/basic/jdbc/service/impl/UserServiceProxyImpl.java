package am.basic.jdbc.service.impl;

import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.DuplicateDataException;
import am.basic.jdbc.model.excpetion.ForbiddenException;
import am.basic.jdbc.model.excpetion.NotFoundException;
import am.basic.jdbc.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceProxyImpl extends UserServiceImpl {


    public UserServiceProxyImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User signIn(String username, String password) throws NotFoundException, ForbiddenException {
        try {
            //begin transaction
            User user = super.signIn(username, password);
            //transaction commit
            return user;
        } catch (RuntimeException e) {
            //transaction rollback
            throw e;
        }


    }


    // @Override
    public User signInFunctional(String username, String password) throws NotFoundException, ForbiddenException {
        return null;

    }

    @Override
    public void signUp(User user) throws DuplicateDataException {

    }

    @Override
    public void verify(String username, String code) throws NotFoundException, ForbiddenException {

    }

    @Override
    public void resend(String username) throws NotFoundException {

    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws ForbiddenException, NotFoundException {

    }

}
