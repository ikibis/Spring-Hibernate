package ru.kibis.carcatalog.model;

public class Gearbox {
    private int id;
    private GearboxType type;

    public Gearbox() {
    }

    public Gearbox(GearboxType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GearboxType getType() {
        return type;
    }

    public void setType(GearboxType type) {
        this.type = type;
    }
}
