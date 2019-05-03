package ru.kibis.car.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private static final Logger LOGGER = LogManager.getLogger(AdStorage.class.getName());
    @Qualifier("adDataRepository")
    @Autowired
    private AdDataRepository adDataRepository;

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
        try {
            adDataRepository.save(ad);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return ad;
    }

    public Ad updateAd(Ad ad, User user, Car car, int year, int mileage, String description) {
        ad.setUser(user);
        ad.setCar(car);
        ad.setYear(year);
        ad.setMileage(mileage);
        ad.setDescription(description);
        adDataRepository.save(ad);
        return ad;
    }

    public Ad updateStatus(Ad ad, Status status) {
        ad.setStatus(status);
        adDataRepository.save(ad);
        return ad;
    }

    public void deleteAd(int id) {
        adDataRepository.deleteById(id);
    }

    public List<Ad> findAds() {
        return adDataRepository.findAll();
    }

    public Ad findById(int id) {
        return adDataRepository.findById(id);
    }

    public List<Ad> findAdsWithPhoto() {
        return adDataRepository.findAdsWithPhoto();
    }

    public List<Ad> findAdsAtLastDay() {
        return adDataRepository.findAdsAtLastDay(new Date(System.currentTimeMillis()));
    }

    public List<Ad> findAdsByBrand(Manufacturer brand) {
        return adDataRepository.findAdsByBrand(brand);
    }

    public List<Ad> findByUser(User user) {
        return adDataRepository.findAllByUser(user);
    }
}
