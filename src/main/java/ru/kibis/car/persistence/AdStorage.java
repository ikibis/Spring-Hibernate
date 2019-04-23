package ru.kibis.car.persistence;

import org.hibernate.query.Query;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.ad.Status;
import ru.kibis.car.model.car.Car;
import ru.kibis.car.model.car.Manufacturer;
import ru.kibis.car.model.user.User;

import java.sql.Date;
import java.util.List;


public class AdStorage {
    private static final AdStorage INSTANCE = new AdStorage();
    private static final StorageWrapper WRAPPER = StorageWrapper.getINSTANCE();

    public static AdStorage getInstance() {
        return INSTANCE;
    }

    public Ad addAd(User user, Car car, int year, int mileage, String description) {
        return WRAPPER.tx(session -> {
            Ad ad = new Ad();
            ad.setUser(user);
            ad.setYear(year);
            ad.setMileage(mileage);
            ad.setDescription(description);
            ad.setStatus(Status.ACTIVE);
            ad.setCar(car);
            ad.setCreateDate(new Date(System.currentTimeMillis()));
            session.saveOrUpdate(ad);
            return ad;
        });
    }

    public Ad updateAd(Ad ad, User user, Car car, int year, int mileage, String description) {
        return WRAPPER.tx(session -> {
            ad.setUser(user);
            ad.setCar(car);
            ad.setYear(year);
            ad.setMileage(mileage);
            ad.setDescription(description);
            session.saveOrUpdate(ad);
            return ad;
        });
    }

    public Ad updateStatus(Ad ad, Status status) {
        return WRAPPER.tx(session -> {
            ad.setStatus(status);
            session.saveOrUpdate(ad);
            return ad;
        });
    }

    public Ad deleteAd(Ad ad) {
        return WRAPPER.tx(session -> {
            session.delete(ad);
            return ad;
        });
    }

    public List<Ad> findAds() {
        return WRAPPER.tx(
                session -> session.createQuery("from Ad").list()
        );
    }

    public List<Ad> findAdsWithPhoto() {
        return WRAPPER.tx(
                session -> session.createQuery("from Ad where id in ( select ad.id from Photo )").list()

        );
    }


    public List<Ad> findAdsAtLastDay() {
        return WRAPPER.tx(
                session -> {
                    Query query = session.createQuery("from Ad where createDate = :param");
                    query.setParameter("param", new Date(System.currentTimeMillis()));
                    return query.list();
                }
        );
    }

    public List<Ad> findAdsByBrand(Manufacturer brand) {
        return WRAPPER.tx(
                session -> {
                    Query query = session.createQuery("from Ad where car.brand = :param");
                    query.setParameter("param", brand);
                    return query.list();
                }
        );
    }


    public Ad findById(int id) {
        return WRAPPER.tx(
                session -> session.get(Ad.class, id)
        );
    }

    public List<Ad> findByUser(User user) {
        return WRAPPER.tx(
                session -> {
                    Query query = session.createQuery("from Ad where user.id = :param");
                    query.setParameter("param", user.getId());
                    return query.list();
                }
        );
    }
}