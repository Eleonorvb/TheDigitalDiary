package com.julias.diary.diary.service.impl;

import com.julias.diary.diary.io.entity.UserEntity;
import com.julias.diary.diary.repository.UserRepository;
import com.julias.diary.diary.service.UserService;
import com.julias.diary.diary.shared.dto.UserDto;
import com.julias.diary.diary.shared.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDto createUser(UserDto user) {
        //Business logic

        if (userRepository.findByEmail(user.getEmail()) !=null) throw new RuntimeException("DENNA FINNS REDAN");
        UserEntity userEntity= new UserEntity();
        BeanUtils.copyProperties(user, userEntity);


        String publicUserId=utils.generateUserId(30);
        userEntity.setUserId(publicUserId);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));


        //Will take user Entity and save to Database

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue= new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }


    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        return null;
    }
}
