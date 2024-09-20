package com.example.vip_rent.modal.entity.fuel_type;

import com.example.vip_rent.enums.FuelTypes;
import com.example.vip_rent.modal.entity.car.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "fuel_type")
public class FuelType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fuel_type_id")
    private Long fuelTypeId;

    @Column(name = "fuel_type_name")
    @Enumerated(EnumType.STRING)
    private FuelTypes fuelTypeName;

    @OneToMany(mappedBy = "fuelType", cascade = CascadeType.ALL)
    private List<Car> carList;
}
