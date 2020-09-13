package com.example.demo.controller;

import com.example.demo.repo.UserRepository;
import com.example.demo.dto.RequestObject;
import com.example.demo.entities.UserdataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JPAController {
    @Autowired
    private UserRepository repository;

    @Transactional
    @RequestMapping(value = "/checking", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public UserdataEntity registerUserNew(@RequestBody UserdataEntity requestObject)
    {


        return repository.save(requestObject);
    }


    //get all users
    @RequestMapping(value = "/getall", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public List<UserdataEntity> getAll() {
        List<UserdataEntity> user = repository.findByUserFirstname("ss");
        return user;

    }




}
