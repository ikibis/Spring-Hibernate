package ru.kibis.car;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import ru.kibis.car.model.*;
import ru.kibis.car.persistence.Storage;

public class StorageTest {
    Storage storage;

    @Before
    public void beforeTests() {
        storage = Storage.getInstance();
    }

    @Test
    public void whenAddCarThenStore() {
        Car car = storage.addCar("Audi", BodyType.SEDAN, new Engine(EngineType.DIESEL, 2.0), GearboxType.AUTOMAT);
        int id = car.getId();
        assertThat(id, is((storage.findById(id)).getId()));
    }

    @Test
    public void whenUpdateCarThenStore() {
        Car car = storage.addCar("Seat", BodyType.HATCHBACK, new Engine(EngineType.HYBRID, 1.4), GearboxType.AUTOMAT);
        int carId = car.getId();
        storage.updateCar(car, "SeatUpdated", car.getBody(), car.getEngine(), car.getGearbox());
        assertThat(car.getName(), is((storage.findById(carId)).getName()));
    }

    @Test
    public void whenDeleteCarThenCheckIt() {
        Car car = storage.addCar("Porshe", BodyType.HATCHBACK, new Engine(EngineType.PETROL, 3.0), GearboxType.ROBOT);
        storage.deleteCar(car);
        assertNull(storage.findById(car.getId()));
    }
}
