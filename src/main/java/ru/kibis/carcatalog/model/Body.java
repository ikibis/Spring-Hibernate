package ru.kibis.carcatalog.model;

public class Body {
    private int id;
    private BodyType type;

    public Body() {
    }

    public Body(BodyType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BodyType getType() {
        return type;
    }

    public void setType(BodyType type) {
        this.type = type;
    }
}
