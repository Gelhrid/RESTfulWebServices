package com.appmichalkodz.app.ws.controller;

import com.appmichalkodz.app.ws.exceotions.UserServiceException;
import com.appmichalkodz.app.ws.service.UserService;
import com.appmichalkodz.app.ws.shared.dto.UserDto;
import com.appmichalkodz.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appmichalkodz.app.ws.ui.model.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value ="page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "25") int limit){

        List<UserRest> list = new ArrayList();
        List<UserDto> lista = userService.getUsers(page, limit);

        //to podspodem przepisac na jave 8!!!
        for(UserDto userDto: lista){
            UserRest userModel = new UserRest();
            BeanUtils.copyProperties(userDto, userModel);
            list.add(userModel);
        }

        List<UserRest> list2 = lista
                .stream()
                .map(this::fff) /// a jak to nie zadzialaa to map(a -> fff(a))
                .collect(Collectors.toList());

        return list;
    }

    public UserRest fff (UserDto userDto){
        UserRest userModel = new UserRest();
        BeanUtils.copyProperties(userDto, userModel);
        return userModel;
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

    @PutMapping( value = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable("id") String userID){

        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updatedUser = userService.updateUser(userID, userDto);

        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
    }

    @DeleteMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel deleteUser(@PathVariable("id") String userID){
        OperationStatusModel returnedValue = new OperationStatusModel();
        returnedValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(userID);
        returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return  returnedValue;

    }

}
