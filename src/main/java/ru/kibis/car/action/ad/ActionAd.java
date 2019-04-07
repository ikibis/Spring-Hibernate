package ru.kibis.car.action.ad;

import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.http.HttpServletRequest;


public interface ActionAd {
    Ad doAction(ValidateServiceAd validateService, HttpServletRequest req);
}
