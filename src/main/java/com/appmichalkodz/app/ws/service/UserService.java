package com.appmichalkodz.app.ws.service;

import com.appmichalkodz.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(String email);

    UserDto getUserByUserId(String userID);

    UserDto updateUser(String id, UserDto userDto);
    void deleteUser(String userId);

    List<UserDto> getUsers(int page, int limit);
}
