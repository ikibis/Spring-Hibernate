package ru.kibis.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kibis.car.domain.Ad;
import ru.kibis.car.domain.Status;
import ru.kibis.car.domain.User;
import ru.kibis.car.domain.car.BodyType;
import ru.kibis.car.domain.car.EngineType;
import ru.kibis.car.domain.car.GearboxType;
import ru.kibis.car.domain.car.Manufacturer;
import ru.kibis.car.repository.AdRepository;
import ru.kibis.car.service.interfaces.AdService;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository repository;

    @Autowired
    public AdServiceImpl(AdRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ad addAd(Ad ad) {
        return this.repository.save(ad);
    }

    @Override
    public Ad updateAd(Ad ad) {
        return this.repository.save(ad);
    }

    @Override
    public List<Map<String, String>> findAllForBoard(String searchType, String brand) {
        List<Ad> fromStorage = new CopyOnWriteArrayList<>();
        Manufacturer brandToSearch = null;
        if (brand != null && !brand.equals("empty")) {
            brandToSearch = Manufacturer.valueOf(brand);
        }
        if (searchType != null) {
            switch (searchType) {
                case "last_day":
                    fromStorage = this.repository.findAdsAtLastDay(new Date(System.currentTimeMillis()));
                    break;
                case "with_photo":
                    fromStorage = this.repository.findAdsWithPhoto();
                    break;
                case "brand":
                    if (brandToSearch != null) {
                        fromStorage = this.repository.findAdsByBrand(brandToSearch);
                    }
                    break;
                default:
                    fromStorage = this.repository.findAll();
                    break;
            }
        } else {
            fromStorage = this.repository.findAll();
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

    @Override
    public Ad findById(int id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Ad> findByUser(User user) {
        return this.repository.findAllByUser(user);
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
            Class enumClass = Class.forName("ru.kibis.car.domain.car.manufacturers." + manufacturer);
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

