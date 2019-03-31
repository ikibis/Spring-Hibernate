package ru.kibis.carcatalog.model;

import java.util.List;

public class Gearbox {
    private int id;
    private String name;
    private List<Gearbox> gearboxes;

    public Gearbox() {
    }

    public Gearbox(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Gearbox> getGearboxes() {
        return gearboxes;
    }

    public void setGearboxes(List<Gearbox> gearboxes) {
        this.gearboxes = gearboxes;
    }

}
