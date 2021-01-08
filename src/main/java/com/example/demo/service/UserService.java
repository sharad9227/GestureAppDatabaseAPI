package com.example.demo.service;

import com.example.demo.dto.ResponseObject;
import com.example.demo.entities.LoginGestureConfigEntity;
import com.example.demo.entities.UsersEntity;
import com.example.demo.repo.LoginGestureRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginGestureRepository loginGestureRepository;
    //creating global objects for reuse
    ResponseObject responseObject = new ResponseObject();
    UsersEntity usersEntity = new UsersEntity();
    LoginGestureConfigEntity loginGesture = new LoginGestureConfigEntity();
    //Creation of timestamp
    Date date = new Date();
    long time = date.getTime();
    Timestamp currentTimestamp = new Timestamp(time);
    String errorMessage;

    /* Service method for registering new user */
    public ResponseEntity<ResponseObject> registerUser(UsersEntity user) throws Exception {
        try {
            user.setLatestUpdated(currentTimestamp);
            usersEntity = this.userRepository.save(user);
            if (usersEntity != null && (usersEntity.getUserId() > 0)) {
                //success flag
                responseObject.setStatus(200);
                responseObject.setMessage("User Registered Successfully. Please use your email id :" + usersEntity.getEmail() + " to log into the application");
            } else {
                //error flag :405 Method Not Allowed :Some processing error
                responseObject.setStatus(405);
                responseObject.setMessage("Error occured in Registration.Please try later");
            }
            return ResponseEntity.ok().body(responseObject);
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseObject.setStatus(500);
            responseObject.setMessage("Exception occurred" + e.getMessage());
            Throwable cause = e;
            if (cause != null && e.getCause().getCause().getMessage().contains("Duplicate entry")) {
                errorMessage = "Duplicate entry.Email already exists";
                //status - 409 conflict for duplicate email
                throw new ResponseStatusException(HttpStatus.CONFLICT, errorMessage, e);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }



    /* Service method for checking if user is valid or not by matching supplied email and password */

    public ResponseEntity<ResponseObject> loginUser(String email, String password) throws Exception {
        HashMap<String, Object> userDetails = new HashMap<String, Object>();
        try {
            usersEntity = userRepository.findByEmailAndPassword(email, password);
            if (usersEntity != null) {
                if (usersEntity.getActiveStatus() && usersEntity.getApprovedStatus()) {
                    //setting login information in login gesture table
                    LoginGestureConfigEntity users = loginGestureRepository.findByUsers(usersEntity);
                    if (users != null) {
                        //user exists
                        if (users.getConfigId() > 0) {
                            //write logic for update
                            int id = users.getConfigId();
                            loginGesture = loginGestureRepository.findById(users.getConfigId()).orElse(null);
                            loginGesture.setLastLoggedIn(currentTimestamp);
                            this.loginGestureRepository.save(loginGesture);
                        }
                    } else {
                        //create user login information
                        loginGesture = new LoginGestureConfigEntity();
                        loginGesture.setUsers(usersEntity);
                        loginGesture.setConfigJsonData(null);
                        loginGesture.setLastLoggedIn(currentTimestamp);
                        this.loginGestureRepository.save(loginGesture);
                    }
                    //putting details in hashmap
                    userDetails.put("user_first_name", usersEntity.getUserFirstName());
                    userDetails.put("userId", usersEntity.getUserId());
                    userDetails.put("userType",usersEntity.getUserType());
                    LoginGestureConfigEntity usersLoggedIn = loginGestureRepository.findByUsers(usersEntity);
                    userDetails.put("configId",usersLoggedIn.getConfigId());
                    responseObject.setStatus(200);
                    responseObject.setMessage("Active and Approved User");
                    responseObject.setResponseObj(userDetails);
                }
            } else {
                //error flag :405 Method Not Allowed :Some processing error
                responseObject.setStatus(405);
                responseObject.setResponseObj(null);
                responseObject.setMessage("Username or password is incorrect.Please try again");
            }
            return ResponseEntity.ok().body(responseObject);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }



    /*
    Service method for updating model json in Gesture table for premium users
     */

    public ResponseEntity<ResponseObject> updateGestureConfig(int configId, String configModel) throws Exception {
        try {
            //usersEntity = userRepository.findByUserId(userId);
            //change it to special later on

            loginGesture = loginGestureRepository.findById(configId).orElse(null);



                if (loginGesture != null) {
                    //user exists
                        String configData = loginGesture.getConfigJsonData();
                            loginGesture.setConfigJsonData(configModel);
                            this.loginGestureRepository.save(loginGesture);
                            responseObject.setStatus(200);
                            responseObject.setMessage("Training Model saved Successfully");
                }
            return ResponseEntity.ok().body(responseObject);
        }


        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }


    /* service method for checking user config data*/

    public ResponseEntity<ResponseObject> getConfig(int configId) throws Exception{
        try{
            HashMap<String, Object> userDetails = new HashMap<String, Object>();
            loginGesture = loginGestureRepository.findById(configId).orElse(null);
            if (loginGesture != null) {
                String configData = loginGesture.getConfigJsonData();
                responseObject.setStatus(200);
                //putting details in hashmap
                userDetails.put("configData",configData);
                 responseObject.setResponseObj(userDetails);
            }
            return ResponseEntity.ok().body(responseObject);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }
    /* service method for getting user information */
    public ResponseEntity<ResponseObject> getUserInformation(int userId) throws Exception
    {
        try {
            HashMap<String, Object> userDetails = new HashMap<String, Object>();
            usersEntity = userRepository.findById(userId).orElse(null);
            if (usersEntity != null) {
                String configData = loginGesture.getConfigJsonData();
                responseObject.setStatus(200);
                //putting details in hashmap
                userDetails.put("userFirstName", usersEntity.getUserFirstName());
                userDetails.put("userLastName", usersEntity.getUserLastName());
                userDetails.put("userType",usersEntity.getUserType());
                userDetails.put("email",usersEntity.getEmail());

              //  userDetails.put("user_last_name")
                responseObject.setResponseObj(userDetails);
            }
            return ResponseEntity.ok().body(responseObject);
        }

        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }

    /* service method for updating user information */
    public ResponseEntity<ResponseObject> updateUserDetails(int userId, String firstName,String lastName,boolean reqFlag) throws Exception {
        try {
            HashMap<String, Object> userDetails = new HashMap<String, Object>();
            usersEntity = userRepository.findById(userId).orElse(null);
            if (usersEntity != null) {
                // null and empty check
                if (firstName != null && firstName.length() > 0) {
                    usersEntity.setUserFirstName(firstName);
                }
                if (lastName != null && lastName.length() > 0) {
                    usersEntity.setUserLastName(lastName);
                }
                usersEntity.setReqStatus(reqFlag);
                this.userRepository.save(usersEntity);
                responseObject.setStatus(200);
                responseObject.setMessage("Details Saved Successfully");
            }
            return ResponseEntity.ok().body(responseObject);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }


    /* service method for getting all users information */
    public List<UsersEntity> getUsersInfo(Integer userId) throws Exception {
        try {
            usersEntity = userRepository.findById(userId).orElse(null);
            return userRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }


        /* service method for updating normal user to premium user */
        public ResponseEntity<ResponseObject> upgradeUser(int userId) throws Exception {
            try {
                String message="User has been upgraded to Premium";
                usersEntity = userRepository.findById(userId).orElse(null);
                if (usersEntity != null) {
                    // null and empty check
                    usersEntity.setUserType("Special");
                    this.userRepository.save(usersEntity);
                    responseObject.setStatus(200);
                    responseObject.setMessage(message);
                }
                return ResponseEntity.ok().body(responseObject);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
            }
        }


    /* service method for deactivating user */
    public ResponseEntity<ResponseObject> deactivateUser(int userId) throws Exception {
        try {
            String message="User has been deactivated";
            usersEntity = userRepository.findById(userId).orElse(null);
            if (usersEntity != null) {
                // null and empty check
                usersEntity.setActiveStatus(false);
                this.userRepository.save(usersEntity);
                responseObject.setStatus(200);
                responseObject.setMessage(message);
            }
            return ResponseEntity.ok().body(responseObject);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e);
        }
    }


}