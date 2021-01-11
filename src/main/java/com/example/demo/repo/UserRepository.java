package com.example.demo.repo;

import com.example.demo.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

   UsersEntity findByEmailAndPassword(String email,String password);


   UsersEntity findByUserId(int userId);

   UsersEntity findByEmail(String Email);


}





