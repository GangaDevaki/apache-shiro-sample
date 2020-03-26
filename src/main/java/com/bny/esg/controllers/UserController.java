package com.bny.esg.controllers;

import java.util.Collection;

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

import com.bny.esg.entity.User;
import com.bny.esg.service.UserService;

@RestController
@RequestMapping(path = "/users",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

	@Autowired
    private UserService userService;

    @GetMapping()
    //@RequiresPermissions("users:read")
    public Collection<User> listUsers() {
        return userService.listUsers();
    }

    @GetMapping(path = "/{id}")
    @RequiresPermissions("users:read")
    public User getUser(@PathVariable("id") String id) throws NotFoundException {

        User user = userService.getUser(id);
        if (user == null) {
            throw new NotFoundException(id);
        }
        return user;
    }

    @PostMapping()
    @RequiresPermissions("users:create")
    public User createUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @PostMapping(path = "/{id}")
    @RequiresPermissions("users:update")
    public User updateUser(@PathVariable("id") String id, @RequestBody User updateUser) throws NotFoundException {

        return userService.updateUser(id, updateUser);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequiresPermissions("users:delete")
    public void deleteUser(@PathVariable("id") String id) {
    	userService.deleteUser(id);
    }


}
