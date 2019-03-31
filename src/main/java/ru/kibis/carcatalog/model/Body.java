package ru.kibis.carcatalog.model;

import java.util.List;

public class Body {
    private int id;
    private String name;
    private List<Body> bodies;

    public Body() {
    }

    public Body(int id) {
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

    public List<Body> getBodies() {
        return bodies;
    }

    public void setBodies(List<Body> bodies) {
        this.bodies = bodies;
    }

}
