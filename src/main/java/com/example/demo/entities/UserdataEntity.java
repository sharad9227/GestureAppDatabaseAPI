package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor

@Entity
@Table(name = "Userdata", schema = "sharad")
public class UserdataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String userFirstname;
    private String userLastname;
    private String userType;
    private String email;
    private String password;

    public UserdataEntity() {

    }

    private String plateNumber;
    private String oauthToken;
    private String sessionWebtoken;
    private String activeStatus;
    private String approvedStatus;
    private Timestamp latestUpdated;
    @OneToMany(targetEntity=ParkingSpotsEntity.class,mappedBy = "parkingOwnerUser")
    private List<ParkingSpotsEntity> parkingSlots;

    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_firstname", nullable = true, length = 255)
    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    @Basic
    @Column(name = "user_lastname", nullable = true, length = 255)
    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    @Basic
    @Column(name = "user_type", nullable = true)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "plate_number", nullable = false, length = 100)
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Basic
    @Column(name = "oauth_token", nullable = false, length = 255)
    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    @Basic
    @Column(name = "session_webtoken", nullable = false, length = 255)
    public String getSessionWebtoken() {
        return sessionWebtoken;
    }

    public void setSessionWebtoken(String sessionWebtoken) {
        this.sessionWebtoken = sessionWebtoken;
    }

    @Basic
    @Column(name = "active_status", nullable = false)
    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Basic
    @Column(name = "approved_status", nullable = false)
    public String getApprovedStatus() {
        return approvedStatus;
    }

    public void setApprovedStatus(String approvedStatus) {
        this.approvedStatus = approvedStatus;
    }

    @Basic
    @Column(name = "latest_updated", nullable = false)

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

        UserdataEntity that = (UserdataEntity) o;

        if (userId != that.userId) return false;
        if (activeStatus != that.activeStatus) return false;
        if (approvedStatus != that.approvedStatus) return false;
        if (userFirstname != null ? !userFirstname.equals(that.userFirstname) : that.userFirstname != null)
            return false;
        if (userLastname != null ? !userLastname.equals(that.userLastname) : that.userLastname != null) return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (plateNumber != null ? !plateNumber.equals(that.plateNumber) : that.plateNumber != null) return false;
        if (oauthToken != null ? !oauthToken.equals(that.oauthToken) : that.oauthToken != null) return false;
        if (sessionWebtoken != null ? !sessionWebtoken.equals(that.sessionWebtoken) : that.sessionWebtoken != null)
            return false;
        if (latestUpdated != null ? !latestUpdated.equals(that.latestUpdated) : that.latestUpdated != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userFirstname != null ? userFirstname.hashCode() : 0);
        result = 31 * result + (userLastname != null ? userLastname.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (plateNumber != null ? plateNumber.hashCode() : 0);
        result = 31 * result + (oauthToken != null ? oauthToken.hashCode() : 0);
        result = 31 * result + (sessionWebtoken != null ? sessionWebtoken.hashCode() : 0);
        result = 31 * result + (activeStatus != null ? userLastname.hashCode() : 0);
        result = 31 * result + (approvedStatus != null ? userLastname.hashCode() : 0);
        result = 31 * result + (latestUpdated != null ? latestUpdated.hashCode() : 0);
        return result;
    }
}
