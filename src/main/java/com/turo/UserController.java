package com.turo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("get/{firstName}")
    public List<User> getByFirstName(final @PathVariable("firstName") String firstName) {
        return userRepository.findByFirstName(firstName);
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
        return userRepository.findByAddress_Zipcode(zipcode);
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public User post(final @RequestBody User user) {
        return userRepository.save(user);
    }
}
