package ru.kibis.car;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import ru.kibis.car.model.car.*;
import ru.kibis.car.persistence.AdStorage;

public class CarStorageTest {
    AdStorage carStorage;

    /*@Before
    public void beforeTests() {
        carStorage = AdStorage.getInstance();
    }

    @Test
    public void whenAddCarThenStore() {
        Car car = carStorage.addCar("Audi", BodyType.SEDAN, new Engine(EngineType.DIESEL, 2.0), GearboxType.AUTOMAT);
        int id = car.getId();
        assertThat(id, is((carStorage.findById(id)).getId()));
    }

    @Test
    public void whenUpdateCarThenStore() {
        Car car = carStorage.addCar("Seat", BodyType.HATCHBACK, new Engine(EngineType.HYBRID, 1.4), GearboxType.AUTOMAT);
        int carId = car.getId();
        carStorage.updateCar(car, "SeatUpdated", car.getBody(), car.getEngine(), car.getGearbox());
        assertThat(car.getName(), is((carStorage.findById(carId)).getName()));
    }

    @Test
    public void whenDeleteCarThenCheckIt() {
        Car car = carStorage.addCar("Porshe", BodyType.HATCHBACK, new Engine(EngineType.PETROL, 3.0), GearboxType.ROBOT);
        carStorage.deleteCar(car);
        assertNull(carStorage.findById(car.getId()));
    }*/
}
