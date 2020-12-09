package com.example.demo.controller;

import com.example.demo.dto.ResponseObject;
import com.example.demo.entities.LoginGestureConfigEntity;
import com.example.demo.entities.UsersEntity;
import com.example.demo.repo.UserRepository;

import com.example.demo.entities.UserdataEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class JPAController {
    @Autowired
    private UserService userService;



    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/users/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseObject> registerUserNew(@RequestBody UsersEntity requestObject) throws Exception {
        return userService.registerUser(requestObject);

    }


    //get all users
   /* @RequestMapping(value = "/getall", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public List<UsersEntity> getAll() {
        List<UsersEntity> user = repository.findByUserFirstname("ss");
        return user;

    }*/

    @CrossOrigin
    @RequestMapping(value = "/users/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject> loginUser(@RequestBody UsersEntity requestObject) throws Exception {
            return userService.loginUser(requestObject.getEmail(),requestObject.getPassword());
    }




    @CrossOrigin
    @RequestMapping(value="/users/update/gestureconfig",method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<ResponseObject> updateUserConfig(@RequestBody LoginGestureConfigEntity requestObject) throws Exception
    {
            return userService.updateGestureConfig(requestObject.getConfigId(),requestObject.getConfigJsonData());
    }


    @CrossOrigin
    @GetMapping("/users/get/{configId}")
    public ResponseEntity<ResponseObject>  getUserConfig(@PathVariable Integer configId) throws Exception {

        return userService.getConfig(configId);
    }



}
