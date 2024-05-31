package com.parking.service.login.repository;

import com.parking.service.login.entities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {
}
