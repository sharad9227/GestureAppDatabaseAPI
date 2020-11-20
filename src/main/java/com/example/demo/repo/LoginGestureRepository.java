package com.example.demo.repo;

import com.example.demo.entities.LoginGestureConfigEntity;
import com.example.demo.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface LoginGestureRepository extends JpaRepository<LoginGestureConfigEntity,Integer> {

        LoginGestureConfigEntity  findByUsers(UsersEntity users);

}
