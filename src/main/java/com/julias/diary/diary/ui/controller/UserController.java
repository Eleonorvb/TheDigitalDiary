package com.julias.diary.diary.ui.controller;


import com.julias.diary.diary.service.UserService;
import com.julias.diary.diary.shared.dto.UserDto;
import com.julias.diary.diary.ui.model.request.UserDetailsRequestModel;
import com.julias.diary.diary.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//http://localhost:8080/users
public class UserController {



    @Autowired
    UserService userService;


    //GET is used to request data from a specified resource.
    @GetMapping
    public  String getUser(){
        return "get user was called";
    }

// POST is used to send data to a server to create
    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails){
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }



    //PUT is used to send data to a server to update a resource.
    @PutMapping
    public String updateUser(){
        return "the update user was called";
    }


    @DeleteMapping
    public  String deleteUser(){
        return "delete user was called";
    }

}
