package com.julias.diary.diary.repository;

import com.julias.diary.diary.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
//Crud eller JpaRepository?
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    //UserEntity findByEmail(String email);
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);


}
