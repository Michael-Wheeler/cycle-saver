package com.cycle_saver.controller;

import com.cycle_saver.model.user.Journey;
import com.cycle_saver.model.user.User;
import com.cycle_saver.services.JourneyService;
import com.cycle_saver.services.UserService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
public class TestController {
    @Autowired
    UserService userService;
    @Autowired
    JourneyService journeyService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        System.out.println("getUsers CALLED");
        return userService.findAllUsers();
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") ObjectId id) {
        return userService.findOneById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User addUser() {
        User user = new User();
        return userService.insert(user);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public Response deleteUser(@PathVariable ObjectId id) {
        userService.deleteUser(userService.findOneById(id));
        return Response.noContent().build();

    }

    @RequestMapping(value = "/clearUsers", method = RequestMethod.DELETE)
    public Response deleteUsers() {
        userService.deleteAll();
        return Response.noContent().build();
    }

    @RequestMapping(value = "/genUsers", method = RequestMethod.POST)
    public Response genUsers() {
        List<User> users = new ArrayList<>();
        List<Journey> journeys = new ArrayList<>();

        users.add(new User("123456789123"));
        users.add(new User("987654321123"));
        users.add(new User("WHY2222222"));
        users = userService.saveAll(users);

        journeys.add(new Journey(users.get(0).getId(), 240));
        journeys.add(new Journey(users.get(0).getId(), 250));
        journeys.add(new Journey(users.get(1).getId(), 240));
        journeys.add(new Journey(users.get(1).getId(), 270));
        journeys.add(new Journey(users.get(1).getId(), 290));
        journeys = journeyService.saveAll(journeys);

        users.get(0).addJourneyId(journeys.get(0).getId());
        users.get(0).addJourneyId(journeys.get(1).getId());
        users.get(1).addJourneyId(journeys.get(2).getId());
        users.get(1).addJourneyId(journeys.get(3).getId());
        users.get(1).addJourneyId(journeys.get(4).getId());
        userService.saveAll(users);
        return Response.ok(users).build();
    }
}
