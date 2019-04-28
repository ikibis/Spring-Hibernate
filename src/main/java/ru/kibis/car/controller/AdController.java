package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kibis.car.action.ad.ActionFactoryAd;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.car.*;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceAd;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdController {
    private final ActionFactoryAd factory = ActionFactoryAd.getInstance();
    private final ValidateServiceAd validateServiceAd = ValidateServiceAd.getInstance();
    private final ValidateServiceUser validateServiceUser = ValidateServiceUser.getInstance();

    @RequestMapping(value = "/ad_servlet", method = RequestMethod.POST)
    public void findAllForBoard(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String showType = req.getParameter("show_type");
        String brand = req.getParameter("brand");
        List<Map<String, String>> ads = validateServiceAd.findAllForBoard(showType, brand);
        this.send(ads, resp);
    }

    @RequestMapping(value = "/ad_servlet", method = RequestMethod.GET)
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("ad_id"));
        Ad ad = validateServiceAd.findById(id);
        this.send(ad, resp);
    }

    @RequestMapping(value = "/ad_create_servlet", method = RequestMethod.GET)
    public void getParts(HttpServletResponse resp) throws IOException {
        List<List<String>> enumValues = new ArrayList<>();
        enumValues.add(validateServiceAd.getBodyTypes());
        enumValues.add(validateServiceAd.getEngineTypes());
        enumValues.add(validateServiceAd.getGearboxTypes());
        enumValues.add(validateServiceAd.getManufacturers());
        this.send(enumValues, resp);
    }

    @RequestMapping(value = "/ad_create_servlet", method = RequestMethod.POST)
    public void createAd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Map<String, String[]> map = req.getParameterMap();
        Car car = new Car(
                Manufacturer.valueOf(map.get("manufacturer")[0]),
                map.get("model")[0],
                BodyType.valueOf(map.get("body_type")[0]),
                new Engine(
                        EngineType.valueOf(map.get("engine_type")[0]),
                        Integer.valueOf(map.get("engine_value")[0])),
                GearboxType.valueOf(map.get("gearbox_type")[0])
        );
        User user = validateServiceUser.findById(Integer.valueOf(map.get("user_id")[0]));
        Ad ad = validateServiceAd.addAd(
                user,
                car,
                Integer.valueOf(map.get("year")[0]),
                Integer.valueOf(map.get("mileage")[0]),
                map.get("description")[0]
        );
        this.send(ad, resp);
    }

    @RequestMapping(value = "/brand_servlet", method = RequestMethod.POST)
    public void getModels(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String manufacturer = req.getParameter("manufacturer");
        List<String> enumValues = validateServiceAd.getModels(manufacturer);
        this.send(enumValues, resp);
    }

    @RequestMapping(value = "/ad_edit_servlet", method = RequestMethod.GET)
    public void getAd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("ad_id"));
        Ad ad = validateServiceAd.findById(id);
        this.send(ad, resp);
    }

    @RequestMapping(value = "/ad_edit_servlet", method = RequestMethod.POST)
    public void updateAd(HttpServletRequest req) {
        factory.action("update", req);
    }

    @RequestMapping(value = "/my_ads_servlet", method = RequestMethod.GET)
    public void getMyAds(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("user_id"));
        User user = validateServiceUser.findById(id);
        List<Ad> ads = validateServiceAd.findByUser(user);
        this.send(ads, resp);
    }

    @RequestMapping(value = "/my_ads_servlet", method = RequestMethod.POST)
    public void updateAdStatus(HttpServletRequest req) {
        factory.action("updateStatus", req);
    }

    private void send(Object object, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(object);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}
