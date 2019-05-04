package ru.kibis.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kibis.car.domain.Ad;
import ru.kibis.car.domain.Status;
import ru.kibis.car.domain.car.*;
import ru.kibis.car.domain.User;
import ru.kibis.car.service.AdService;
import ru.kibis.car.service.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {
    private final AdService adService;
    private final UserService userService;

    @Autowired
    public AdController(AdService adService, UserService userService) {
        this.adService = adService;
        this.userService = userService;
    }

    @PostMapping(value = "/ad_servlet", produces = "application/json")
    @ResponseBody
    public List findAllForBoard(
            @RequestParam("show_type") String showType,
            @RequestParam("brand") String brand) {
        return adService.findAllForBoard(showType, brand);
    }

    @GetMapping(value = "/ad_servlet", produces = "application/json")
    @ResponseBody
    public Ad findById(@RequestParam("ad_id") int id) {
        return adService.findById(id);
    }

    @GetMapping(value = "/ad_create_servlet", produces = "application/json")
    @ResponseBody
    public List getParts() {
        List<List<String>> enumValues = new ArrayList<>();
        enumValues.add(adService.getBodyTypes());
        enumValues.add(adService.getEngineTypes());
        enumValues.add(adService.getGearboxTypes());
        enumValues.add(adService.getManufacturers());
        return enumValues;
    }

    @PostMapping(value = "/ad_create_servlet", produces = "application/json")
    @ResponseBody
    public Ad createAd(
            @RequestParam("manufacturer") String manufacturer,
            @RequestParam("model") String model,
            @RequestParam("body_type") String bodyType,
            @RequestParam("engine_type") String engineType,
            @RequestParam("engine_value") int engineValue,
            @RequestParam("gearbox_type") String gearboxType,
            @RequestParam("user_id") int userId,
            @RequestParam("year") int year,
            @RequestParam("mileage") int mileage,
            @RequestParam("description") String description
    ) {
        Car car = new Car(
                Manufacturer.valueOf(manufacturer),
                model,
                BodyType.valueOf(bodyType),
                new Engine(
                        EngineType.valueOf(engineType),
                        engineValue
                ),
                GearboxType.valueOf(gearboxType)
        );
        User user = userService.findById(userId);
        Ad ad = new Ad(car, user, year, mileage, description, Status.ACTIVE, new Date(System.currentTimeMillis()));
        return adService.addAd(ad);
    }

    @PostMapping(value = "/brand_servlet", produces = "application/json")
    @ResponseBody
    public List getModels(@RequestParam("manufacturer") String manufacturer) {
        return adService.getModels(manufacturer);
    }

    @GetMapping(value = "/ad_edit_servlet", produces = "application/json")
    @ResponseBody
    public Ad getAd(@RequestParam("ad_id") int adId) {
        return adService.findById(adId);
    }

    @PostMapping(value = "/ad_edit_servlet", produces = "application/json")
    @ResponseBody
    public Ad updateAd(
            @RequestParam("user_id") int userId,
            @RequestParam("ad_id") int adId,
            @RequestParam("manufacturer") String manufacturer,
            @RequestParam("model") String model,
            @RequestParam("body_type") String bodyType,
            @RequestParam("engine_type") String engineType,
            @RequestParam("engine_value") int engineValue,
            @RequestParam("gearbox_type") String gearboxType,
            @RequestParam("year") int year,
            @RequestParam("mileage") int mileage,
            @RequestParam("description") String description
    ) {
        Car car = new Car(
                Manufacturer.valueOf(manufacturer),
                model,
                BodyType.valueOf(bodyType),
                new Engine(
                        EngineType.valueOf(engineType),
                        engineValue),
                GearboxType.valueOf(gearboxType)
        );
        User user = userService.findById(userId);
        Ad ad = adService.findById(adId);
        ad.setUser(user);
        ad.setCar(car);
        ad.setYear(year);
        ad.setMileage(mileage);
        ad.setDescription(description);
        return adService.updateAd(ad);
    }

    @GetMapping(value = "/my_ads_servlet", produces = "application/json")
    @ResponseBody
    public List getMyAds(@RequestParam("user_id") int userId) {
        User user = userService.findById(userId);
        return adService.findByUser(user);
    }

    @PostMapping(value = "/my_ads_servlet", produces = "application/json")
    @ResponseBody
    public Ad updateAdStatus(
            @RequestParam("ad_id") int adId,
            @RequestParam("ad_status") String adStatus) {
        Ad ad = adService.findById(adId);
        ad.setStatus(Status.valueOf(adStatus));
        return adService.updateAd(ad);
    }
}