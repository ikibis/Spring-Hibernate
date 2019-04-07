package ru.kibis.car.action.ad;

import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.ad.Status;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class UpdateStatus implements ActionAd {
    ValidateServiceAd validateServiceAd = ValidateServiceAd.getInstance();
    @Override
    public Ad doAction(ValidateServiceAd validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        return validateServiceAd.updateStatus(
                validateServiceAd.findById(Integer.valueOf(map.get("ad_id")[0])),
                Status.valueOf(map.get("ad_status")[0])
        );
    }
}
