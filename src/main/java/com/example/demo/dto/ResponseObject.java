package com.example.demo.dto;

import java.util.HashMap;

public class ResponseObject {
    // Response Message for Success / Error
    private String message;
    public int status;
    private HashMap<String, Object> responseObj;

    public HashMap<String, Object> getResponseObj() {
        return responseObj;
    }

    public void setResponseObj(HashMap<String, Object> responseObj) {
        this.responseObj = responseObj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
