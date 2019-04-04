package ru.kibis.car.model;

import javax.persistence.*;

@Entity
@Table(name = "car_engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    private EngineType type;

    @Column(name = "value")
    private double value;

    public Engine() {
    }

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
}
