package com.julias.diary.diary.service;

import com.julias.diary.diary.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/*
Interface with methods for the UserService
 */

public interface UserService extends UserDetailsService{
    public UserDto createUser(UserDto user);
    UserDto getUser (String email);
    UserDto getUserByUserId(String userId);


}
