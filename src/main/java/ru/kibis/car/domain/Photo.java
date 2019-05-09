package ru.kibis.car.domain;

import org.springframework.data.annotation.PersistenceConstructor;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private byte[] photo;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public Photo() {
    }

    @PersistenceConstructor
    public Photo(byte[] photo, String name, Ad ad) {
        this.photo = photo;
        this.name = name;
        this.ad = ad;
    }

    /*public Photo(byte[] photo) {
        this.photo = photo;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
