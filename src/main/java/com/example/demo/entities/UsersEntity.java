package com.example.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Users", schema = "sharad")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "user_firstname", nullable = true, length = 255)
    private String userFirstName;

    @Column(name = "user_lastname", nullable = true, length = 255)
    private String userLastName;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "active_status", nullable = false, columnDefinition = "boolean default true")
    private boolean activeStatus=true;

    @Column(name = "approved_status", nullable = false,columnDefinition = "boolean default true")
    private boolean approvedStatus =true;

    @Column(name = "latest_updated",nullable = false)

    private Timestamp latestUpdated ;



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }


    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }


    public boolean getApprovedStatus() {
        return approvedStatus;
    }

    public void setApprovedStatus(boolean approvedStatus) {
        this.approvedStatus = approvedStatus;
    }


    public Timestamp getLatestUpdated() {
        return latestUpdated;
    }

    public void setLatestUpdated(Timestamp latestUpdated) {
        this.latestUpdated = latestUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return userId == that.userId &&
                activeStatus == that.activeStatus &&
                approvedStatus == that.approvedStatus &&
                Objects.equals(userFirstName, that.userFirstName) &&
                Objects.equals(userLastName, that.userLastName) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(latestUpdated, that.latestUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userFirstName, userLastName, userType, email, password, activeStatus, approvedStatus, latestUpdated);
    }
}
