package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.ad.Status;
import ru.kibis.car.model.car.*;
import ru.kibis.car.model.user.User;
import ru.kibis.car.persistence.AdStorage;
import ru.kibis.car.repository.AdDataRepository;
import ru.kibis.car.service.ValidateServiceAd;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("spring-context.xml");
    private static final ValidateServiceAd VALIDATE_SERVICE_AD = CONTEXT.getBean(ValidateServiceAd.class);
    private static final ValidateServiceUser VALIDATE_SERVICE_USER = CONTEXT.getBean(ValidateServiceUser.class);

    @RequestMapping(value = "/ad_servlet", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List findAllForBoard(
            @RequestParam("show_type") String showType,
            @RequestParam("brand") String brand) {
        return VALIDATE_SERVICE_AD.findAllForBoard(showType, brand);
    }

    @RequestMapping(value = "/ad_servlet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Ad findById(@RequestParam("ad_id") int id) {
        return VALIDATE_SERVICE_AD.findById(id);
    }

    @RequestMapping(value = "/ad_create_servlet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List getParts() {
        List<List<String>> enumValues = new ArrayList<>();
        enumValues.add(VALIDATE_SERVICE_AD.getBodyTypes());
        enumValues.add(VALIDATE_SERVICE_AD.getEngineTypes());
        enumValues.add(VALIDATE_SERVICE_AD.getGearboxTypes());
        enumValues.add(VALIDATE_SERVICE_AD.getManufacturers());
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
            //@RequestParam("engine") String engineJson,
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
        User user = VALIDATE_SERVICE_USER.findById(userId);
        return VALIDATE_SERVICE_AD.addAd(
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
        return VALIDATE_SERVICE_AD.getModels(manufacturer);
    }

    @RequestMapping(value = "/ad_edit_servlet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Ad getAd(@RequestParam("ad_id") int adId) {
        return VALIDATE_SERVICE_AD.findById(adId);
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
        User user = VALIDATE_SERVICE_USER.findById(userId);
        Ad ad = VALIDATE_SERVICE_AD.findById(adId);
        return VALIDATE_SERVICE_AD.updateAd(
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
        User user = VALIDATE_SERVICE_USER.findById(userId);
        return VALIDATE_SERVICE_AD.findByUser(user);
    }

    @RequestMapping(value = "/my_ads_servlet", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Ad updateAdStatus(
            @RequestParam("ad_id") int adId,
            @RequestParam("ad_status") String adStatus) {
        Ad ad = VALIDATE_SERVICE_AD.findById(adId);
        Status status = Status.valueOf(adStatus);
        return VALIDATE_SERVICE_AD.updateStatus(ad, status);
    }
}
