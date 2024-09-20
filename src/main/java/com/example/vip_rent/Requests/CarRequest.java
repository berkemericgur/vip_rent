package com.example.vip_rent.Requests;

import com.example.vip_rent.modal.entity.fuel_type.FuelType;
import com.example.vip_rent.modal.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequest {

    private String brand;

    private String model;

    private String plate;

    private String year;

    private String description;

    private FuelType fuelType;

    private User user;
}
