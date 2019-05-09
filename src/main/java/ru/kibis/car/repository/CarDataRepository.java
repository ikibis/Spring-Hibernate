package ru.kibis.car.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kibis.car.model.car.Car;

public interface CarDataRepository extends CrudRepository<Car, Integer> {

}
