package ru.kibis.car.service;

import ru.kibis.car.domain.Ad;
import ru.kibis.car.domain.Status;
import ru.kibis.car.domain.User;
import ru.kibis.car.domain.car.BodyType;
import ru.kibis.car.domain.car.EngineType;
import ru.kibis.car.domain.car.GearboxType;
import ru.kibis.car.domain.car.Manufacturer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface AdService {
    Ad addAd(Ad ad);

    Ad updateAd(Ad ad);

    void deleteById(int id);

    List<Map<String, String>> findAllForBoard(String searchType, String brand);

    Ad findById(int id);

    List<Ad> findByUser(User user);

    List<String> getAdStatuses();

    List<String> getBodyTypes();

    List<String> getEngineTypes();

    List<String> getGearboxTypes();

    List<String> getManufacturers();

    List<String> getModels(String manufacturer);
}


