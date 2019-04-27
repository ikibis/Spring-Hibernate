package ru.kibis.car.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BrandServlet extends HttpServlet {
    private final ValidateServiceAd validateServiceAd = ValidateServiceAd.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String manufacturer = req.getParameter("manufacturer");
        List<String> enumValues = validateServiceAd.getModels(manufacturer);
        resp.setContentType("text/html");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(enumValues);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}
