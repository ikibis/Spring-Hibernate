package ru.kibis.car.persistence;

import ru.kibis.car.model.BodyType;
import ru.kibis.car.model.Car;
import ru.kibis.car.model.Engine;
import ru.kibis.car.model.GearboxType;

import java.util.List;

public class Storage {
    private static final Storage INSTANCE = new Storage();
    private static final StorageWrapper WRAPPER = StorageWrapper.getINSTANCE();

    public static Storage getInstance() {
        return INSTANCE;
    }

    public Car addCar(String name, BodyType body, Engine engine, GearboxType gearbox) {
        return WRAPPER.tx(session -> {
            Car car = new Car();
            car.setName(name);
            car.setBody(body);
            car.setEngine(engine);
            car.setGearbox(gearbox);
            session.saveOrUpdate(car);
            return car;
        });
    }

    public Car updateCar(Car car, String name, BodyType body, Engine engine, GearboxType gearbox) {
        return WRAPPER.tx(session -> {
            car.setName(name);
            car.setBody(body);
            car.setEngine(engine);
            car.setGearbox(gearbox);
            session.saveOrUpdate(car);
            return car;
        });
    }

    public Car deleteCar(Car car) {
        return WRAPPER.tx(session -> {
            session.delete(car);
            return car;
        });
    }

    public List<Car> findCars() {
        return WRAPPER.tx(
                session -> session.createQuery("from Car").list()
        );
    }

    public Car findById(int id) {
        return WRAPPER.tx(
                session -> session.get(Car.class, id)
        );
    }
}