package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ru.kibis.car.action.ad.ActionFactoryAd;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceAd;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MyAdsServlet extends HttpServlet {

    private final ValidateServiceAd validateServiceAd = ValidateServiceAd.getInstance();
    private final ValidateServiceUser validateServiceUser = ValidateServiceUser.getInstance();
    private final ActionFactoryAd factory = ActionFactoryAd.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("user_id"));
        User user = validateServiceUser.findById(id);
        List<Ad> ad = validateServiceAd.findByUser(user);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(ad);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        factory.action("updateStatus", req);
    }
}