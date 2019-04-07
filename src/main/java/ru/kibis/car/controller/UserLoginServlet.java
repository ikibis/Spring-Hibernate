package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для входа пользователя в систему
 */
public class UserLoginServlet extends HttpServlet {
    /**
     * Сервис валидации
     */
    private final ValidateServiceUser validateService = ValidateServiceUser.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = validateService.isCredentional(login, password);
        JSONObject json = new JSONObject();
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            json.put("login", login);
            json.put("id", user.getId());
        } else {
            json.put("login", "error");
        }
        this.send(json, resp);
    }

    private void send(JSONObject json, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(json);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}