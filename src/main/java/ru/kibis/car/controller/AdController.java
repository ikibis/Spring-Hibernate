package ru.kibis.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.ad.Status;
import ru.kibis.car.model.car.*;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceAd;
import ru.kibis.car.service.ValidateServiceUser;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {
    private final ValidateServiceAd validateServiceAd = ValidateServiceAd.getInstance();
    private final ValidateServiceUser validateServiceUser = ValidateServiceUser.getInstance();

    @RequestMapping(value = "/ad_servlet", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List findAllForBoard(
            @RequestParam("show_type") String showType,
            @RequestParam("brand") String brand) {
        return validateServiceAd.findAllForBoard(showType, brand);
    }

    @RequestMapping(value = "/ad_servlet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Ad findById(@RequestParam("ad_id") int id) {
        return validateServiceAd.findById(id);
    }

    @RequestMapping(value = "/ad_create_servlet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List getParts() {
        List<List<String>> enumValues = new ArrayList<>();
        enumValues.add(validateServiceAd.getBodyTypes());
        enumValues.add(validateServiceAd.getEngineTypes());
        enumValues.add(validateServiceAd.getGearboxTypes());
        enumValues.add(validateServiceAd.getManufacturers());
        return enumValues;
    }

    @RequestMapping(value = "/ad_create_servlet", method = RequestMethod.POST, produces = "application/json")
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
                        engineValue),
                GearboxType.valueOf(gearboxType)
        );
        User user = validateServiceUser.findById(userId);
        return validateServiceAd.addAd(
                user,
                car,
                year,
                mileage,
                description
        );
    }

    @RequestMapping(value = "/brand_servlet", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List getModels(@RequestParam("manufacturer") String manufacturer) {
        return validateServiceAd.getModels(manufacturer);
    }

    @RequestMapping(value = "/ad_edit_servlet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Ad getAd(@RequestParam("ad_id") int adId) {
        return validateServiceAd.findById(adId);
    }

    @RequestMapping(value = "/ad_edit_servlet", method = RequestMethod.POST, produces = "application/json")
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
        User user = validateServiceUser.findById(userId);
        Ad ad = validateServiceAd.findById(adId);
        return validateServiceAd.updateAd(
                ad,
                user,
                car,
                year,
                mileage,
                description
        );
    }

    @RequestMapping(value = "/my_ads_servlet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List getMyAds(@RequestParam("user_id") int userId) {
        User user = validateServiceUser.findById(userId);
        return validateServiceAd.findByUser(user);
    }

    @RequestMapping(value = "/my_ads_servlet", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Ad updateAdStatus(
            @RequestParam("ad_id") int adId,
            @RequestParam("ad_status") String adStatus) {
        Ad ad = validateServiceAd.findById(adId);
        Status status = Status.valueOf(adStatus);
        return validateServiceAd.updateStatus(ad, status);
    }
}
