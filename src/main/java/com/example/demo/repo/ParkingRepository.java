package com.example.demo.repo;

import com.example.demo.entities.ParkingSpotsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ParkingRepository extends JpaRepository<ParkingSpotsEntity, Integer> {
}
