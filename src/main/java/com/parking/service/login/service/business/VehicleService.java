package com.parking.service.login.service.business;

import com.parking.service.login.entities.Vehicles;

import java.util.Optional;

public interface VehicleService {
    Vehicles saveVehicle (Vehicles vehicles);
    Optional<Vehicles> finVehicleById (Integer vehicleId);
}
