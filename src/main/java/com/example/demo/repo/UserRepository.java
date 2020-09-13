package com.example.demo.repo;

import com.example.demo.entities.UserdataEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<UserdataEntity, Integer> {

List<UserdataEntity> findByUserFirstname(String userFirstname);

}
