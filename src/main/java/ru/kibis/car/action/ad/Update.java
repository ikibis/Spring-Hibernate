package ru.kibis.car.action.ad;

import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.ad.Status;
import ru.kibis.car.model.car.*;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceAd;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class Update implements ActionAd {
    ValidateServiceAd validateServiceAd = ValidateServiceAd.getInstance();
    ValidateServiceUser validateServiceUser = ValidateServiceUser.getInstance();
    @Override
    public Ad doAction(ValidateServiceAd validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        Car car = new Car(
                map.get("car_name")[0],
                BodyType.valueOf(map.get("body_type")[0]),
                new Engine(
                        EngineType.valueOf(map.get("engine_type")[0]),
                        Integer.valueOf(map.get("engine_value")[0])),
                GearboxType.valueOf(map.get("gearbox_type")[0])
        );
        System.out.println(car.getName());
        User user = validateServiceUser.findById(Integer.valueOf(map.get("user_id")[0]));
        Ad ad = validateServiceAd.findById(Integer.valueOf(map.get("ad_id")[0]));
        return validateService.updateAd(
                ad,
                user,
                car,
                Integer.valueOf(map.get("year")[0]),
                Integer.valueOf(map.get("mileage")[0]),
                map.get("description")[0]
        );
    }
}
