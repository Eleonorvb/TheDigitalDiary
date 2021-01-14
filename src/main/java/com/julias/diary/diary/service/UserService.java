package com.julias.diary.diary.service;

import com.julias.diary.diary.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    public UserDto createUser(UserDto user);
}
