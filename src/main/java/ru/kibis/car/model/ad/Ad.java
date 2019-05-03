package ru.kibis.car.model.ad;

import org.springframework.data.annotation.PersistenceConstructor;
import ru.kibis.car.model.car.Car;
import ru.kibis.car.model.user.User;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    private int year;
    private int mileage;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name ="status")
    private Status status;

    @Column(name ="createdate")
    private Date createDate;

    public Ad() {
    }
    @PersistenceConstructor
    public Ad(Car car, User user, int year, int mileage, String description, Status status, Date createDate) {
        this.car = car;
        this.user = user;
        this.year = year;
        this.mileage = mileage;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
