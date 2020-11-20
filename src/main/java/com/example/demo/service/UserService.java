package com.example.demo.service;

import com.example.demo.dto.ResponseObject;
import com.example.demo.entities.LoginGestureConfigEntity;
import com.example.demo.entities.UsersEntity;
import com.example.demo.repo.LoginGestureRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginGestureRepository loginGestureRepository;

    //creating global objects for reuse
    ResponseObject responseObject =new ResponseObject();
    UsersEntity usersEntity = new UsersEntity();
    LoginGestureConfigEntity loginGesture=new LoginGestureConfigEntity();
    //Creation of timestamp
    Date date= new Date();
    long time = date.getTime();
    Timestamp currentTimestamp = new Timestamp(time);
    /* Service method for registering new user */
    public ResponseEntity<ResponseObject> registerUser(UsersEntity user) throws Exception {

        try {


            user.setLatestUpdated(currentTimestamp);
            usersEntity = this.userRepository.save(user);
            if( usersEntity!=null && (usersEntity.getUserId() > 0))
            {
                //success flag
                responseObject.setStatus(200);
                responseObject.setMessage("User Registered Successfully. Please use your email id :" + usersEntity.getEmail() +" to log into the application");
                //responseObject.setResponseObj();
            }
            else
            {
                //error flag :405 Method Not Allowed :Some processing error(id =0) or something
                responseObject.setStatus(405);
                responseObject.setMessage("Error occured in Registration.Please try later");
            }

        }
        catch (Exception e)
        {
            //logic bypassing .. Need to check later by handling exception
            responseObject.setStatus(500);
            responseObject.setMessage("Exception occurred"+e.getMessage());
            throw new Exception(e.getMessage());
        }
        return ResponseEntity.ok().body(responseObject);
    }



    /* Service method for checking if user is valid or not by matching supplied email and password */


    public ResponseEntity<ResponseObject> loginUser(String email,String password) throws Exception
    {
        HashMap<String, Object> userDetails = new HashMap<String, Object>();
        try{
            usersEntity = userRepository.findByEmailAndAndPassword(email,password);
            if(usersEntity!=null) {
                if (usersEntity.getActiveStatus() && usersEntity.getApprovedStatus()) {
                    //setting login information in login gesture table
                    LoginGestureConfigEntity users= loginGestureRepository.findByUsers(usersEntity);
                    //user exists
                    if(users.getConfigId()>0){
                     //write logic for update


                        
                    }
                    else{
                        loginGesture.setUsers(usersEntity);
                        loginGesture.setConfigJsonData(null);
                        loginGesture.setLastLoggedIn(currentTimestamp);
                        this.loginGestureRepository.save(loginGesture);
                    }
                    //putting details in hashmap
                    userDetails.put("user_first_name", usersEntity.getUserFirstName());
                    userDetails.put("userId", usersEntity.getUserId());
                    responseObject.setStatus(200);
                    responseObject.setMessage("Active and Approved User");
                    responseObject.setResponseObj(userDetails);
                }
            }
            else
            {
                //error flag :405 Method Not Allowed :Some processing error
                responseObject.setStatus(405);
                responseObject.setResponseObj(null);
                responseObject.setMessage("Username or password is incorrect.Please try again");

            }





        }
        catch (Exception e) {

            throw new Exception(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(responseObject);
    }








}
