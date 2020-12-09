package com.example.demo.repo;

import com.example.demo.entities.LoginGestureConfigEntity;
import com.example.demo.entities.UserdataEntity;
import com.example.demo.entities.UsersEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

   UsersEntity findByEmailAndAndPassword(String email,String password);


   UsersEntity findByUserId(int userId);



}





