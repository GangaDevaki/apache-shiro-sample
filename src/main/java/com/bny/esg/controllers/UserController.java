package com.bny.esg.controllers;

import com.bny.esg.dto.UserDao;
import com.bny.esg.model.User;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/users",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    @RequiresPermissions("users:read")
    public Collection<User> listUsers() {
        return userDao.listUsers();
    }

    @GetMapping(path = "/{id}")
    @RequiresPermissions("users:read")
    public User getUser(@PathVariable("id") String id) throws NotFoundException {

        User user = userDao.getUser(id);
        if (user == null) {
            throw new NotFoundException(id);
        }
        return user;
    }

    @PostMapping()
    @RequiresPermissions("users:create")
    public User createUser(@RequestBody User user) {

        return userDao.addUser(user);
    }

    @PostMapping(path = "/{id}")
    @RequiresPermissions("users:update")
    public User updateUser(@PathVariable("id") String id, @RequestBody User updateUser) throws NotFoundException {

        return userDao.updateUser(id, updateUser);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequiresPermissions("users:delete")
    public void deleteUser(@PathVariable("id") String id) {
        userDao.deleteUser(id);
    }


}
