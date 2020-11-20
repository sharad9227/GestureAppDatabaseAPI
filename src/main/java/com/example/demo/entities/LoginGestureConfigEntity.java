package com.example.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Gesture", schema = "sharad")
public class LoginGestureConfigEntity {

    @Id
    @Column(name = "config_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int configId;


    @Column(name = "last_logged_in", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp lastLoggedIn;


    @Column(name = "config_json_data", nullable = true, length = -1)
    private String configJsonData;

    @OneToOne
    @JoinColumn(name="gesture_user_id",insertable=true,updatable = false)
    private UsersEntity users;

    public UsersEntity getUsers() {
        return users;
    }

    public void setUsers(UsersEntity users) {
        this.users = users;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }


    public Timestamp getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Timestamp lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }


    public String getConfigJsonData() {
        return configJsonData;
    }

    public void setConfigJsonData(String configJsonData) {
        this.configJsonData = configJsonData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginGestureConfigEntity that = (LoginGestureConfigEntity) o;
        return configId == that.configId &&
                Objects.equals(lastLoggedIn, that.lastLoggedIn) &&
                Objects.equals(configJsonData, that.configJsonData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configId, lastLoggedIn, configJsonData);
    }
}
