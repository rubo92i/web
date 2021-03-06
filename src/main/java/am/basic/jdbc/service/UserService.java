package am.basic.jdbc.service;

import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.DuplicateDataException;
import am.basic.jdbc.model.excpetion.ForbiddenException;
import am.basic.jdbc.model.excpetion.NotFoundException;

public interface UserService {


    User signIn(String username, String password) throws NotFoundException, ForbiddenException;

    void signUp(User user) throws  DuplicateDataException;

    void verify(String username, String code) throws NotFoundException, ForbiddenException;

    void resend(String username) throws NotFoundException;

    void changePassword(String username, String oldPassword, String newPassword) throws ForbiddenException, NotFoundException;

}
