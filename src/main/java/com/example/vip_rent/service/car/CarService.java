package com.example.vip_rent.service.car;

import com.example.vip_rent.Requests.CarRequest;
import com.example.vip_rent.modal.entity.car.Car;
import com.example.vip_rent.modal.entity.fuel_type.FuelType;
import com.example.vip_rent.modal.entity.user.User;
import com.example.vip_rent.repository.car.CarRepository;
import com.example.vip_rent.repository.fuel_type.FuelTypeRepository;
import com.example.vip_rent.repository.user.UserRepository;
import com.example.vip_rent.result.DataResult;
import com.example.vip_rent.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService{

    private final CarRepository carRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final UserRepository userRepository;

    @Override
    public DataResult<List<Car>> getAllCars() {

        List<Car> carList = this.carRepository.findAll();

        if (carList.isEmpty())
            return new DataResult<>(null,
                    Result.showMessage(Result.SERVER_ERROR, "Cars not found!"));
        return new DataResult<>(carList,
                Result.showMessage(Result.SUCCESS, "Cars listed successfully"));
    }

    @Override
    public DataResult<Car> getCarById(Long carId) {

        Optional<Car> carOptional = this.carRepository.findById(carId);

        if(carOptional.isEmpty())
            return new DataResult<>(null,
                    Result.showMessage(Result.SERVER_ERROR, "Car not found!"));
        return new DataResult<>(carOptional.get(),
                Result.showMessage(Result.SUCCESS, "Car listed successfully."));
    }

    @Override
    public DataResult<Car> saveCar(CarRequest request) {

        Optional<FuelType> fuelType = this.fuelTypeRepository.findFuelTypeByFuelTypeName(request.getFuelType());
        if (fuelType.isEmpty())
            return new DataResult<>(null,
                    Result.showMessage(Result.SERVER_ERROR, "Fuel type is not found"));

        Optional<User> user = this.userRepository.findUserByUserName(request.getUser());
        if (user.isEmpty())
            return new DataResult<>(null,
                    Result.showMessage(Result.SERVER_ERROR, "User is not found"));

        Car createdCar = Car.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .year(request.getYear())
                .plate(request.getPlate())
                .description(request.getDescription())
                .fuelType(fuelType.get())
                .user(user.get())
                .build();

        try {
            this.carRepository.save(createdCar);
        }
        catch (Exception e){

            return new DataResult<>(null,
                    Result.showMessage(Result.SERVER_ERROR, "Car not saved"));
        }
        return new DataResult<>(createdCar,
                Result.showMessage(Result.SUCCESS, "Car saved successfully"));

    }

    @Override
    public Result updateCar(Long carId, CarRequest request) {

        Optional<Car> isExistCar = this.carRepository.findById(carId);

        if(isExistCar.isEmpty())
            return new Result(Result.showMessage(Result.SERVER_ERROR, "Car not found"));

        Car updatedCar = isExistCar.get();

        updatedCar.setBrand(request.getBrand());
        updatedCar.setPlate(request.getPlate());
        updatedCar.setModel(request.getModel());
        updatedCar.setUser(request.getUser());
        updatedCar.setYear(request.getYear());
        updatedCar.setDescription(request.getDescription());
        updatedCar.setFuelType(request.getFuelType());
        updatedCar.setUser(request.getUser());

        try{

            this.carRepository.save(updatedCar);
        }
        catch (Exception e){

            return Result.showMessage(Result.SERVER_ERROR, "Failed to update car ");
        }
        return Result.showMessage(Result.SUCCESS, "Car updated successfullu");
    }

    @Override
    public Result deleteCar(Long carId) {

        if (carId == null)
            return Result.showMessage(Result.SERVER_ERROR, "Car id cannot be empty");

        Optional<Car> carOptional = this.carRepository.findById(carId);

        if (carOptional.isPresent()) {
            try {

                this.carRepository.delete(carOptional.get());
                return Result.showMessage(Result.SUCCESS, "Car deleted successfully");
            }
            catch (Exception e) {

                return Result.showMessage(Result.SERVER_ERROR, "Car delete failed");
            }
        }
        return Result.showMessage(Result.SUCCESS, "Car not found");
    }
}
