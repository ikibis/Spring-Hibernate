package ru.kibis.car.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class UserController {
    private final ValidateServiceUser validateService = ValidateServiceUser.getInstance();

    @RequestMapping(value = "/user_create_servlet", method = RequestMethod.POST)
    public void createUser(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("city") String city
    ) {
        validateService.add(
                login,
                password,
                new Timestamp(System.currentTimeMillis()),
                name,
                email,
                city
        );
    }

    @RequestMapping(value = "/user_update_servlet", method = RequestMethod.POST)
    public void updateUser(
            @RequestParam("id") int id,
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("city") String city
    ) {
        User user = validateService.findById(id);
        validateService.update(
                user,
                login,
                password,
                name,
                email,
                city
        );
    }

    @RequestMapping(value = "/user_update_servlet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUser(@RequestParam("id") int id) {
        return validateService.findById(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public JSONObject logIn(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            HttpSession session
    ) {
        User user = validateService.isCredentional(login, password);
        JSONObject json = new JSONObject();
        if (user != null) {
            session.setAttribute("login", login);
            json.put("login", login);
            json.put("id", user.getId());
        } else {
            json.put("login", "error");
        }
        return json;
    }

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public void logOut(HttpSession session) {
        session.invalidate();
    }
}