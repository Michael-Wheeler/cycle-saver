package com.cycle_saver.dataFetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cycle_saver.model.user.User;
import com.cycle_saver.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

    private final UserService userService;

    @Autowired
    AllUsersDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> get(DataFetchingEnvironment env) {
        User user = env.getSource();
        List<User> users = new ArrayList<>();
        if(user == null){
            users = userService.findAllUsers();
        }
        return users;
    }
}
