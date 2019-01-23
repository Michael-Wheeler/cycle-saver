package com.cycle_saver.controller;

import com.cycle_saver.model.user.Journey;
import com.cycle_saver.model.user.User;
import com.cycle_saver.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class TestController{

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        System.out.println("getUsers CALLED");
        List<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") ObjectId id) {
        return userRepository.findById(id);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public List<User> fuckIt() {
        List<User> users = new ArrayList<>();
        users.add(new User("123456789123"));
        users.add(new User("987654321123"));
        users.add(new User("WHY2222222"));
        userRepository.saveAll(users);
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey("123456789", 240));
        journeys.add(new Journey("123456789", 250));
        journeys.add(new Journey("987654321", 240));
        journeys.add(new Journey("987654321", 270));
        journeys.add(new Journey("987654321", 290));

        users.get(0).setJourneyIds(Arrays.asList(journeys.get(0).getId()));
        users.get(0).setJourneyIds(Arrays.asList(journeys.get(1).getId()));
        users.get(1).setJourneyIds(Arrays.asList(journeys.get(2).getId()));
        users.get(1).setJourneyIds(Arrays.asList(journeys.get(3).getId()));
        users.get(1).setJourneyIds(Arrays.asList(journeys.get(4).getId()));

        return userRepository.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Response addUser() {
        userRepository.save(new User());
        return Response.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable ObjectId id) {
        userRepository.delete(userRepository.findById(id));
    }

    @RequestMapping(value = "/clearUsers", method = RequestMethod.DELETE)
    public void deleteUsers() {
        userRepository.deleteAll();
    }

}
