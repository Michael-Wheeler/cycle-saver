package com.cycle_saver.controller.user;

import com.cycle_saver.dto.UserDto;
import com.cycle_saver.model.user.User;

public class UserCodec {

    public static User fromDto(UserDto dto) {
        return new User();
    }

    public static UserDto toDto(User user) {
        return new UserDto();
    }
}
