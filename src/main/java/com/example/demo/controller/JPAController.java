package com.example.demo.controller;

import com.example.demo.dto.ResponseObject;
import com.example.demo.entities.LoginGestureConfigEntity;
import com.example.demo.entities.UsersEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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


    //get all users:: admin page request
    @CrossOrigin
    @GetMapping( "users/getall/{userId}")
    public List<UsersEntity> getAll(@PathVariable Integer userId) throws Exception{
        return userService.getUsersInfo(userId);

    }

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

    @CrossOrigin
    @GetMapping("/users/get/user/{userId}")
    public ResponseEntity<ResponseObject> getUserInformation(@PathVariable Integer userId) throws Exception {

        return userService.getUserInformation(userId);
    }





    @CrossOrigin
    @RequestMapping(value="/users/update/updateUserDetails",method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject> updateUserDetails(@RequestBody UsersEntity requestObject) throws Exception
    {
        return userService.updateUserDetails(requestObject.getUserId(),requestObject.getUserFirstName(),requestObject.getUserLastName(),requestObject.isReqStatus());
    }

    @CrossOrigin
    @RequestMapping(value="/users/update/upgradeUser",method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject> upgradeUser(@RequestBody UsersEntity requestObject) throws Exception
    {
        return userService.upgradeUser(requestObject.getUserId());
    }
    @CrossOrigin
    @RequestMapping(value="/users/update/deactivateUser",method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject> deactivateUser(@RequestBody UsersEntity requestObject) throws Exception
    {
        return userService.deactivateUser(requestObject.getUserId());
    }

}
