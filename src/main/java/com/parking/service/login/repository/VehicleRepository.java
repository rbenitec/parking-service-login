package com.parking.service.login.repository;

import com.parking.service.login.entities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {
}
