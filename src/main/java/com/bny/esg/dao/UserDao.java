package com.bny.esg.dto;

import java.util.Collection;

import com.bny.esg.model.User;

/**
 * Example CRUD DAO interface.
 */
public interface UserDao {

    Collection<User> listUsers();

    User getUser(String id);

    User addUser(User stormtrooper);

    User updateUser(String id, User stormtrooper);

    boolean deleteUser(String id);
}
