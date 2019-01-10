package com.cycle_saver.controller;

import com.cycle_saver.controller.user.UserAuthRequest;
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
        UserAuthRequest user = gson.fromJson(body, UserAuthRequest.class);
        // check if user already exists, add them or send error
        User u = queryUserByEmail(user.getEmail());
        if (u == null) {
            // we have a new user! woohoo, add them
            u = new User();
            // add user via dao/dto layer
            insertUser(u);
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new LoginError(1, "User already exists")).build();
        }
        return Response.ok(provideToken(u)).build();
    }

    @RequestMapping(value = "/signup/strava", method = RequestMethod.POST)
    public Response signUpWithStrava(String body) {
        Gson gson = new Gson();
        UserAuthRequest user = gson.fromJson(body, UserAuthRequest.class);
        // check if user already exists, add them or send error
        User u = queryUserByEmail(user.getEmail());
        if (u == null) {
            // we have a new user! woohoo, add them
            u = new User();
            // add user via dao/dto layer
            insertUser(u);
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new LoginError(1, "User already exists")).build();
        }
        return Response.ok(provideToken(u)).build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(String body) {
        // return jwt with user details to be stored on FE and send in each request for further auth
        Gson gson = new Gson();
        UserAuthRequest authRequest = gson.fromJson(body, UserAuthRequest.class);
        User u = checkLogin(authRequest);
        if (u == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new LoginError(2, "Unrecognised credentials")).build();
        }
        String jwt = provideToken(u);
        return Response.ok(jwt).build();
    }

    @RequestMapping(value = "/login/strava", method = RequestMethod.POST)
    public Response loginWithStrava(String body) {
        // return jwt with user details to be stored on FE and send in each request for further auth
        Gson gson = new Gson();
        UserAuthRequest authRequest = gson.fromJson(body, UserAuthRequest.class);
        User u = checkLogin(authRequest);
        String jwt = provideToken(u);
        return Response.ok(jwt).build();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response logout(@RequestHeader("Authorization") String token) {
        // invalidate token and respond wiht logout message so FE removes token and logs user out
        // or force it onto a login check endpoint which will then fail as token is invalid and then
        // FE has no option but to terminate session??
        return Response.ok().build();
    }

    private class LoginError {
        private int code;
        private String message;

        LoginError(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

