package ru.kibis.car.domain.car;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
@Entity
@Table(name = "car_catalog")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    private Manufacturer brand;

    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    private BodyType body;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Enumerated(EnumType.STRING)
    private GearboxType gearbox;

    public Car() {
    }

    @PersistenceConstructor
    public Car(Manufacturer brand, String model, BodyType body, Engine engine, GearboxType gearbox) {
        this.brand = brand;
        this.model = model;
        this.body = body;
        this.engine = engine;
        this.gearbox = gearbox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Manufacturer getBrand() {
        return brand;
    }

    public void setBrand(Manufacturer brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public BodyType getBody() {
        return body;
    }

    public void setBody(BodyType body) {
        this.body = body;
    }

    public GearboxType getGearbox() {
        return gearbox;
    }

    public void setGearbox(GearboxType gearbox) {
        this.gearbox = gearbox;
    }
}
