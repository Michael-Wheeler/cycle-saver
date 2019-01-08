package com.cycle_saver.controller;

import com.cycle_saver.model.user.User;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/user")
public class AuthController extends BaseController {

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Response signUp(String body) {
        Gson gson = new Gson();
        User user = gson.fromJson(body, User.class);
        // check if user already exists, add them or send error
        User u = queryUser(user);
        if (u == null) {
            // we have a new user! woohoo, add them
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity(new UserSignUp(1, "User already exists")).build();
        }
        return Response.ok(provideToken(user, false)).build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(String body) {
        // return jwt with user details to be stored on FE and send in each request for further auth
        Gson gson = new Gson();
        User user = gson.fromJson(body, User.class);
        String jwt = provideToken(user, true);
        return Response.ok(jwt).build();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response logout(@RequestHeader("Authorization") String token) {
        return Response.ok().build();
    }

    private class UserSignUp {
        private int code;
        private String message;

        public UserSignUp(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
