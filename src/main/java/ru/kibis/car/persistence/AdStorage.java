package ru.kibis.car.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.ad.Status;
import ru.kibis.car.model.car.Car;
import ru.kibis.car.model.car.Manufacturer;
import ru.kibis.car.model.user.User;
import ru.kibis.car.repository.AdDataRepository;

import java.sql.Date;
import java.util.List;

@Component
public class AdStorage {

    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("spring-context.xml");
    private static final AdDataRepository REPOSITORY = CONTEXT.getBean(AdDataRepository.class);

    @Autowired
    public AdStorage() {
    }

    public Ad addAd(User user, Car car, int year, int mileage, String description) {
        Ad ad = new Ad();
        ad.setUser(user);
        ad.setYear(year);
        ad.setMileage(mileage);
        ad.setDescription(description);
        ad.setStatus(Status.ACTIVE);
        ad.setCar(car);
        ad.setCreateDate(new Date(System.currentTimeMillis()));
        REPOSITORY.save(ad);
        return ad;
    }

    public Ad updateAd(Ad ad, User user, Car car, int year, int mileage, String description) {
        ad.setUser(user);
        ad.setCar(car);
        ad.setYear(year);
        ad.setMileage(mileage);
        ad.setDescription(description);
        REPOSITORY.save(ad);
        return ad;
    }

    public Ad updateStatus(Ad ad, Status status) {
        ad.setStatus(status);
        REPOSITORY.save(ad);
        return ad;
    }

    public void deleteAd(int id) {
        REPOSITORY.deleteById(id);
    }

    public List<Ad> findAds() {
        return REPOSITORY.findAll();
    }

    public Ad findById(int id) {
        return REPOSITORY.findById(id);
    }

    public List<Ad> findAdsWithPhoto() {
        return REPOSITORY.findAdsWithPhoto();
    }

    public List<Ad> findAdsAtLastDay() {
        return REPOSITORY.findAdsAtLastDay(new Date(System.currentTimeMillis()));
    }

    public List<Ad> findAdsByBrand(Manufacturer brand) {
        return REPOSITORY.findAdsByBrand(brand);
    }
    public List<Ad> findByUser(User user) {
        return REPOSITORY.findAllByUser(user);
    }
}
