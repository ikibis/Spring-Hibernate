package ru.kibis.carcatalog;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import ru.kibis.carcatalog.model.Body;
import ru.kibis.carcatalog.model.Car;
import ru.kibis.carcatalog.model.Engine;
import ru.kibis.carcatalog.model.Gearbox;
import ru.kibis.carcatalog.persistence.Storage;

public class StorageTest {
    Storage storage;

    @Before
    public void beforeTests() {
        storage = Storage.getInstance();
       /* storage.findCars().stream().forEach(car -> {
            storage = Storage.getInstance();
            storage.deleteCar(car);
        });*/
    }

    @Test
    public void whenAddCarThenStore() {
        Car car = storage.addCar("Audi", new Body(2), new Engine(2), new Gearbox(2));
        int id = car.getId();
        assertThat(id, is((storage.findById(id)).getId()));
    }

    @Test
    public void whenUpdateCarThenStore() {
        Car car = storage.addCar("Seat", new Body(1), new Engine(1), new Gearbox(1));
        int carId = car.getId();
        storage.updateCar(car, "SeatUpdated", car.getBody(), car.getEngine(), car.getGearbox());
        assertThat(car.getName(), is((storage.findById(carId)).getName()));
    }

    @Test
    public void whenDeleteCarThenCheckIt() {
        Car car = storage.addCar("Porshe", new Body(1), new Engine(1), new Gearbox(1));
        storage.deleteCar(car);
        assertNull(storage.findById(car.getId()));
    }
}
