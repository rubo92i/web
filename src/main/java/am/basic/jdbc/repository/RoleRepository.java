package am.basic.jdbc.repository;

import am.basic.jdbc.model.Role;

public interface RoleRepository {

    Role getById(long id);
}
