package com.appmichalkodz.app.ws.service.impl;

import com.appmichalkodz.app.ws.io.entity.UserEntity;
import com.appmichalkodz.app.ws.io.repositories.UserRepository;
import com.appmichalkodz.app.ws.service.UserService;
import com.appmichalkodz.app.ws.shared.Utils;
import com.appmichalkodz.app.ws.shared.dto.UserDto;
import com.appmichalkodz.app.ws.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDto createUser(UserDto userDto) {
       if(userRepository.findByEmail(userDto.getEmail()) != null){
           throw new RuntimeException("no nie bangla bo juz jest taki gosc");
       }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto newUserDto = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, newUserDto);

        return newUserDto;
    }

    @Override
    public UserDto getUserByUserId(String userID) {
        UserEntity userEntity = userRepository.findByUserId(userID);

        if(userEntity == null){
            throw  new UsernameNotFoundException(userID);
        }

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;

    }

    @Override
    public UserDto updateUser(String userID, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userID);
        UserDto returnValue = new UserDto();
        if(userEntity == null){
            throw  new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        UserEntity updatedEntity = userRepository.save(userEntity);//czy to konieczne jak i tak na innym obiekci eto zmienialem, utaj teoretycznie wiem ze zmiana nastopila i bedzie zapisana

        BeanUtils.copyProperties(updatedEntity, returnValue);


        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        //to raczej nie optymalne!!
        //sprovowac co sie stanie jak custom deleta zrobic bede chcial na jakims obikecie  co nie istnieje -> delete By id i ppodam zly id
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null){
            throw  new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        userRepository.delete(userEntity);
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null){
            throw  new UsernameNotFoundException(email);
        }

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity.equals(null)){
            throw new UsernameNotFoundException(email);
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
