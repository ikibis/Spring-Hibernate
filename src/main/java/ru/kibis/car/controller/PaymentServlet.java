package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentServlet extends HttpServlet {
   /* private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        Hall place = validateService.getPlaceById(id);
        JSONObject json = new JSONObject();
        json.put("row", place.getRow());
        json.put("place", place.getPlace());
        this.send(json, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int placeId = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        boolean result = validateService.booking(placeId, name, phone);
        JSONObject json = new JSONObject();
        json.put("result", result);
        this.send(json, resp);
    }

    private void send(JSONObject json, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(json);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }*/
}
