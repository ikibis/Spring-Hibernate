package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HallServlet extends HttpServlet {
   /* private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JSONArray array = new JSONArray();
        List<Hall> places = validateService.findPlaces();
        for (Hall place : places) {
            JSONObject json = new JSONObject();
            json.put("id", place.getPlaceId());
            json.put("row", place.getRow());
            json.put("place", place.getPlace());
            if (place.isAvailable()) {
                json.put("availability", "Available");
            } else {
                json.put("availability", "Busy");
            }
            array.add(json);
        }
        String jsonInString = mapper.writeValueAsString(array);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }*/
}
