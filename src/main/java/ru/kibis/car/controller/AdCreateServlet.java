package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ru.kibis.car.action.ad.ActionFactoryAd;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.car.*;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceAd;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdCreateServlet extends HttpServlet {

    private final ActionFactoryAd factory = ActionFactoryAd.getInstance();
    private final ValidateServiceAd validateServiceAd = ValidateServiceAd.getInstance();
    private final ValidateServiceUser validateServiceUser = ValidateServiceUser.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        ObjectMapper mapper = new ObjectMapper();
        List<List<String>> enumValues = new ArrayList<>();
        enumValues.add(validateServiceAd.getBodyTypes());
        enumValues.add(validateServiceAd.getEngineTypes());
        enumValues.add(validateServiceAd.getGearboxTypes());
        enumValues.add(validateServiceAd.getManufacturers());
        String jsonInString = mapper.writeValueAsString(enumValues);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        resp.setContentType("text/html");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(ad);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}