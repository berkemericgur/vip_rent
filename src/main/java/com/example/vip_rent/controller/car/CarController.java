package com.example.vip_rent.controller.car;

import com.example.vip_rent.Requests.CarRequest;
import com.example.vip_rent.modal.entity.car.Car;
import com.example.vip_rent.result.DataResult;
import com.example.vip_rent.result.Result;
import com.example.vip_rent.service.car.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/car")
@RequiredArgsConstructor
public class CarController {

    private final ICarService carService;

    @GetMapping(value = "/getAll")
    public DataResult<List<Car>> getAll(){

        return this.carService.getAllCars();
    }

    @GetMapping(value = "/getById")
    public DataResult<Car> getById(@RequestParam Long carId){

        return this.carService.getCarById(carId);
    }

    @PostMapping(value = "/saveCar")
    public DataResult<Car> saveCar(@RequestBody CarRequest request){

        return this.carService.saveCar(request);
    }

    @PutMapping(value = "/updateCar")
    public Result updateCar(@RequestParam Long carId, @RequestBody CarRequest request){

        return this.carService.updateCar(carId, request);
    }

    @DeleteMapping(value = "/deleteCar")
    public Result deleteCar(@RequestParam Long carId){

        return this.carService.deleteCar(carId);
    }
}
