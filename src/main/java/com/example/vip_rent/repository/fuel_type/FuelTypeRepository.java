package com.example.vip_rent.repository.fuel_type;

import com.example.vip_rent.modal.entity.fuel_type.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

    Optional<FuelType> findFuelTypeByFuelTypeName(FuelType fuelType);
}
