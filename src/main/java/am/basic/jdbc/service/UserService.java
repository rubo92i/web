package am.basic.jdbc.service;

import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.DuplicateDataException;
import am.basic.jdbc.model.excpetion.NotFoundException;

public interface UserService {


    User signIn(String username, String password) throws NotFoundException;

    void signUp(User user) throws  DuplicateDataException;
}
