package ru.kibis.carcatalog.model;

public class Engine {
    private int id;
    private EngineType type;

    public Engine() {
    }

    public Engine(EngineType type) {
        this.type = type;
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
