package ru.kibis.car.action.ad;

import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class ActionFactoryAd {
    private static class Holder {
        private static final ActionFactoryAd INSTANCE = new ActionFactoryAd();
    }

    public static ActionFactoryAd getInstance() {
        return Holder.INSTANCE;
    }


    private ValidateServiceAd service = ValidateServiceAd.getInstance();

    private Map<String, ActionAd> actionMap = new HashMap<>();

    {
        actionMap.put("create", new Create());
        actionMap.put("update", new Update());
        actionMap.put("delete", new Delete());

        actionMap.put("updateStatus", new UpdateStatus());
    }

    public Ad action(String action, HttpServletRequest req) {
        return actionMap.getOrDefault(action, new UnknownAction()).doAction(service, req);
    }

    class UnknownAction implements ActionAd {

        @Override
        public Ad doAction(ValidateServiceAd validateService, HttpServletRequest req) {
            return null;
        }

    }
}
