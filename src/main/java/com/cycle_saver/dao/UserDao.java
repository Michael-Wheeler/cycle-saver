package com.cycle_saver.dao;

import com.cycle_saver.dto.UserDto;

import java.util.List;

public interface UserDao {

    UserDto addUser(UserDto user);
    UserDto getUser(int userId);

    List<UserDto> getByEmail(String email);
    List<UserDto> getByName(String name);

    void delUser(UserDto userDto);
}
