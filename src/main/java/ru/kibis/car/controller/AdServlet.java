package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class AdServlet extends HttpServlet {

    private final ValidateServiceAd validateService = ValidateServiceAd.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String showType = req.getParameter("show_type");
        String brand = req.getParameter("brand");
        List<Map<String, String>> ads = validateService.findAllForBoard(showType, brand);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(ads);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("ad_id"));
        ObjectMapper mapper = new ObjectMapper();
        Ad ad = validateService.findById(id);
        String jsonInStringAd = mapper.writeValueAsString(ad);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInStringAd);
        writer.flush();
    }
}

