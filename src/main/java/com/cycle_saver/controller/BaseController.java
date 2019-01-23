package com.cycle_saver.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cycle_saver.model.security.Jwt;
import com.cycle_saver.model.user.User;
import com.cycle_saver.services.UserService;
import com.cycle_saver.services.impl.UserServiceImpl;

import javax.ws.rs.ForbiddenException;
import java.util.Date;
import java.util.UUID;

/**
 * Simple class to provide some methods for authenticating and checking jwt tokens
 * for auth and obtaining users from requests
 */
public class BaseController {

    private static final String issuer = "cyclesaver";
    protected static final String webUrl = "http://localhost:3000";

    /**
     * decode token and check user auth pass, returning user to system
     * @param token base64 encoded jwt token
     * @return
     */
    public User checkAuth(String token) throws JWTVerificationException {
        token = extractToken(token);
        DecodedJWT jwt = JWT.decode(token);
        Jwt systemJwt = getSessionToken(jwt.getClaim("jti").asString());
        Algorithm algorithm = Algorithm.HMAC256(systemJwt.getSalt());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .acceptExpiresAt(systemJwt.getExpiry().getTime())
                .acceptIssuedAt(systemJwt.getIssued().getTime())
                .build();
        verifier.verify(token);
        int userId = jwt.getClaim("user").asInt();
        // query user on user id
        //TODO Remove this
        User user = new User("00000000000");
        if (user == null) {
            throw new ForbiddenException("Invalid token");
        }
        return user;
    }

    /**
     * Provide a signed up/in user with a token for the FE to provide on subsequent requests
     * for authentication
     * @param user Successfully logged in user
     * @return a base64 encoded, signed jwt containing user info in payload for FE
     */
    public String provideToken(User user, boolean checkUserExists) {
        // check user exists, based on provided credentials
        if (checkUserExists) {
            //user = queryUser(user);
        }
        String salt = UUID.randomUUID().toString();
        Algorithm algorithm = Algorithm.HMAC256(salt);
        String id = UUID.randomUUID().toString();
        // add token to db
        Jwt systemJwt = new Jwt();
        systemJwt.setIssued(new Date());
        systemJwt.setExpiry(new Date(new Date().getTime() + 3600000));
        systemJwt.setId(id);
        //systemJwt.setUserId(user.getId());
        systemJwt.setSalt(salt);
        String jwt = JWT.create()
                .withIssuer(issuer)
                .withClaim("user", user.getId())
                .withIssuedAt(systemJwt.getIssued())
                .withExpiresAt(systemJwt.getExpiry())
                .withJWTId(id)
                .sign(algorithm);
        return jwt;
    }

//    public User queryUser(User user) {
//        return queryUser(user.getId());
//    }

//    public User queryUser(int userId) {
//        User u = new User();
//        u.setId(userId);
//        return u;
//    }

    public void invalidateToken(String jwt) {
        jwt = extractToken(jwt);
        DecodedJWT djwt = JWT.decode(jwt);
        // update system jwt with invalid values so next check fails
        Jwt systemJwt = getSessionToken(djwt.getClaim("jti").asString());
        systemJwt.setSalt("");
        // set expired
        systemJwt.setExpiry(new Date(new Date().getTime() - 10000));
        // DB update
    }

    private Jwt getSessionToken(String tokenId) {
        return new Jwt();
    }

    private String extractToken(String token) {
        String[] parts = token.split("Bearer ");
        if (parts.length != 2) {
            throw new ForbiddenException("Invalid jwt");
        }
        return parts[1];
    }

}
