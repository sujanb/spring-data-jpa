package com.turo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final int PAGE_SIZE = 2;
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("get/{firstName}")
    public List<User> getByFirstName(final @PathVariable("firstName") String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @ResponseBody
    @RequestMapping("get/page/{page}")
    public Page<User> getAll(final @PathVariable("page") Integer page, final @PathParam("sort") Sort.Direction sort) {
        return userRepository.findAll(new PageRequest(page, PAGE_SIZE, sort, "id"));
    }

    @ResponseBody
    @RequestMapping("get/{firstName}/{lastName}")
    public List<User> getByFirstAndLastName(final @PathVariable("firstName") String firstName,
                                      final @PathVariable("lastName") String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @ResponseBody
    @RequestMapping("get/zipcode/{zipcode}")
    public List<User> getByZipcode(final @PathVariable("zipcode") String zipcode) {
        return userRepository.findByAddressZipcode(zipcode);
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public User post(final @RequestBody User user) {
        return userRepository.save(user);
    }
}
