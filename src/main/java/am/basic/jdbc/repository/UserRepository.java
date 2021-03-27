package am.basic.jdbc.repository;

import am.basic.jdbc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {


    void add(User user);

    void update(User user);

    void delete(long id);

    User getById(long id);

    Optional<User> getByUsername(String username);

    List<User> getAll();

}
