package com.appmichalkodz.app.ws.io.repositories;

import com.appmichalkodz.app.ws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findUserEntityByEmail(String email); //sprawdzic czy moze byc findUser bez entityw nazwie
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
}
