package com.bny.esg.app.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bny.esg.app.entity.User;
import com.bny.esg.app.service.UserService;
import com.bny.esg.app.util.NotFoundException;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public Collection<User> listUsers() {
		return userService.listUsers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public User getUser(@PathVariable("id") Long id) throws NotFoundException {

		User user = userService.getUser(id);
		if (user == null) {
			throw new NotFoundException(String.valueOf(id));
		}
		return user;
	}

/*	@PostMapping()
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
	}*/

}
