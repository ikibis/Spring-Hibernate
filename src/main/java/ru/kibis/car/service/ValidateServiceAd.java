package ru.kibis.car.service;

import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.ad.Status;
import ru.kibis.car.model.car.*;
import ru.kibis.car.model.user.User;
import ru.kibis.car.persistence.AdStorage;
import ru.kibis.car.persistence.PhotoStorage;

import javax.persistence.EnumType;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ValidateServiceAd {

    private final AdStorage storage = AdStorage.getInstance();
    private final PhotoStorage photoStorage = PhotoStorage.getInstance();

    private static class Holder {
        private static final ValidateServiceAd INSTANCE = new ValidateServiceAd();
    }

    public static ValidateServiceAd getInstance() {
        return Holder.INSTANCE;
    }

    public Ad addAd(User user, Car car, int year, int mileage, String description) {
        Ad result = null;
        if (user != null
                && car != null
                && year > 0
                && mileage >= 0
                && description != null) {
            result = storage.addAd(user, car, year, mileage, description);
        }
        return result;
    }

    public Ad updateAd(Ad ad, User user, Car car, int year, int mileage, String description) {
        Ad result = null;
        if (ad != null
                && user != null
                && car != null
                && year > 0
                && mileage >= 0
                && description != null
        ) {
            result = storage.updateAd(ad, user, car, year, mileage, description);
        }
        return result;
    }

    public Ad updateStatus(Ad ad, Status status) {
        Ad result = null;
        if (ad != null && status != null) {
            result = storage.updateStatus(ad, status);
        }
        return result;
    }

    public void deleteAd(int id) {
        Ad ad = this.findById(id);
        storage.deleteAd(ad);
    }

    public List<Map<String, String>> findAllForBoard(String searchType, String brand) {
        List<Ad> fromStorage = new CopyOnWriteArrayList<>();
        Manufacturer brandToSearch = null;
        if (brand != null && !brand.equals("empty")) {
            brandToSearch = Manufacturer.valueOf(brand);
        }
        if (searchType != null) {
            switch (searchType) {
                case "last_day":
                    fromStorage = storage.findAdsAtLastDay();
                    break;
                case "with_photo":
                    fromStorage = storage.findAdsWithPhoto();
                    break;
                case "brand":
                    if (brandToSearch != null) {
                        fromStorage = storage.findAdsByBrand(brandToSearch);
                    }
                    break;
                default:
                    fromStorage = storage.findAds();
                    break;
            }
        } else {
            fromStorage = storage.findAds();
        }

        List<Map<String, String>> result = new ArrayList<>();
        int i = 0;
        for (Ad ad : fromStorage) {
            Map<String, String> collected = new HashMap<>();
            collected.put("id", String.valueOf(ad.getId()));
            collected.put("brand", ad.getCar().getBrand().toString());
            collected.put("model", ad.getCar().getModel());
            collected.put("year", String.valueOf(ad.getYear()));
            collected.put("mileage", String.valueOf(ad.getMileage()));
            result.add(i++, collected);
        }
        return result;
    }

    public Ad findById(int id) {
        return storage.findById(id);
    }

    public List<Ad> findByUser(User user) {
        return storage.findByUser(user);
    }

    public List<String> getAdStatuses() {
        return Arrays.stream(
                Status.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    public List<String> getBodyTypes() {
        return Arrays.stream(
                BodyType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    public List<String> getEngineTypes() {
        return Arrays.stream(
                EngineType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    public List<String> getGearboxTypes() {
        return Arrays.stream(
                GearboxType.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    public List<String> getManufacturers() {
        return Arrays.stream(
                Manufacturer.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    public List<String> getModels(String manufacturer) {
        List<String> models = new ArrayList<>();
        try {
            Class enumClass = Class.forName("ru.kibis.car.model.car.manufacturers." + manufacturer);
            Object[] obj = enumClass.getEnumConstants();
            for (Object o : obj) {
                models.add(o.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return models;
    }
}
