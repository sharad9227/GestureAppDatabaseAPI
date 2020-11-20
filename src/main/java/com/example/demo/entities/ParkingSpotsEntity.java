package com.example.demo.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "parking_spots", schema = "sharad")
public class ParkingSpotsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private boolean activeStatus;
    private Integer vacantSpots;
    private Timestamp createdTimestamp;
    private Timestamp updatedTime;
    private int parking_user_id;
    @ManyToOne
    @JoinColumn(name="parking_user_id",insertable=false,updatable = false)
    private UserdataEntity parkingOwnerUser;



    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "latitude", nullable = false, precision = 6)
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "Longitude", nullable = false, precision = 6)
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "active_status", nullable = false)
    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Basic
    @Column(name = "vacant_spots", nullable = true)
    public Integer getVacantSpots() {
        return vacantSpots;
    }

    public void setVacantSpots(Integer vacantSpots) {
        this.vacantSpots = vacantSpots;
    }

    @Basic
    @Column(name = "CREATED_TIMESTAMP", nullable = false)
    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    @Basic
    @Column(name = "parking_user_id", nullable = true)
    public int getParking_user_id() {
        return parking_user_id;
    }

    public void setParking_user_id(int parking_user_id) {
        this.parking_user_id = parking_user_id;
    }

    @Basic
    @Column(name = "UPDATED_TIME", nullable = false)
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingSpotsEntity that = (ParkingSpotsEntity) o;

        if (id != that.id) return false;
        if (activeStatus != that.activeStatus) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (vacantSpots != null ? !vacantSpots.equals(that.vacantSpots) : that.vacantSpots != null) return false;
        if (createdTimestamp != null ? !createdTimestamp.equals(that.createdTimestamp) : that.createdTimestamp != null)
            return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (activeStatus ? 1 : 0);
        result = 31 * result + (vacantSpots != null ? vacantSpots.hashCode() : 0);
        result = 31 * result + (createdTimestamp != null ? createdTimestamp.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);

        return result;
    }


}
