package com.example.vip_rent.service.car;

import com.example.vip_rent.Requests.CarRequest;
import com.example.vip_rent.modal.entity.car.Car;
import com.example.vip_rent.result.DataResult;
import com.example.vip_rent.result.Result;

import java.util.List;

public interface ICarService {

    DataResult<List<Car>> getAllCars();
    DataResult<Car> getCarById(Long carId);
    DataResult<Car> saveCar(CarRequest request);
    Result updateCar(Long carId, CarRequest request);
    Result deleteCar(Long carId);

}
