package ru.kibis.car.action.ad;

import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class Delete implements ActionAd {

    @Override
    public Ad doAction(ValidateServiceAd validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        int idToDelete = Integer.valueOf(Objects.requireNonNull(map.get("id")[0]));
       /* if (validateService.findById(idToDelete) != null) {
            validateService.delete(idToDelete);
        }*/
       return null;
    }
}
