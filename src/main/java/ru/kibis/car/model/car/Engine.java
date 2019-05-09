package ru.kibis.car.model.car;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
@Entity
@Table(name = "car_engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private EngineType type;

    private double value;

    public Engine() {
    }

    @PersistenceConstructor
    public Engine(EngineType type, double value) {
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EngineType getType() {
        return type;
    }

    public void setType(EngineType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
