package com.appmichalkodz.app.ws.controller;

import com.appmichalkodz.app.ws.exceotions.UserServiceException;
import com.appmichalkodz.app.ws.service.UserService;
import com.appmichalkodz.app.ws.shared.dto.UserDto;
import com.appmichalkodz.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appmichalkodz.app.ws.ui.model.response.ErrorMessages;
import com.appmichalkodz.app.ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable("id") String userID){
        UserRest returnValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(userID);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PostMapping( produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                  consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws UserServiceException {

        UserRest returnValue = new UserRest();

        if(userDetails.getFirstName().isEmpty()){
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @PutMapping
    public String updateUser(){
        return " update user called";
    }

    @DeleteMapping
    public String deleteUser(){
        return  "delete user called";

    }

}
