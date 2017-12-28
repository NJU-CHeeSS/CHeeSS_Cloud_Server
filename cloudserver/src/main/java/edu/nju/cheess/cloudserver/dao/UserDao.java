package edu.nju.cheess.cloudserver.dao;

import edu.nju.cheess.cloudserver.entity.User;

public interface UserDao {

    User getUserById(Long id);

    void updateUser(User user);

    Long addUser(User user);
}
