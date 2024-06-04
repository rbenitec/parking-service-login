package com.parking.service.login.service.business.impl;

import com.parking.service.login.entities.Vehicles;
import com.parking.service.login.repository.VehicleRepository;
import com.parking.service.login.service.business.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicles saveVehicle(Vehicles vehicles) {
        return vehicleRepository.save(vehicles);
    }
}