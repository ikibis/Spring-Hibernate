package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kibis.car.action.user.ActionFactoryUser;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {
    private final ActionFactoryUser factory = ActionFactoryUser.getInstance();
    private final ValidateServiceUser validateService = ValidateServiceUser.getInstance();

    @RequestMapping(value = "/user_create_servlet", method = RequestMethod.POST)
    public void createUser(HttpServletRequest req) {
        factory.action("create", req);
    }

    @RequestMapping(value = "/user_update_servlet", method = RequestMethod.POST)
    public void updateUser(HttpServletRequest req) {
        factory.action("update", req);
    }

    @RequestMapping(value = "/user_update_servlet", method = RequestMethod.GET)
    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validateService.findById(id);
        this.send(user, resp);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public void logOut(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
    }

    private void send(Object object, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(object);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}
