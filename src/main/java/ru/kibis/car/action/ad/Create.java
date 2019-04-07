package ru.kibis.car.action.ad;

import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.http.HttpServletRequest;

public class Create implements ActionAd {


    @Override
    public Ad doAction(ValidateServiceAd validateService, HttpServletRequest req) {
        return null;
    }
}


