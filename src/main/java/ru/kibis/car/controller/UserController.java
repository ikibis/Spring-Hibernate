package ru.kibis.car.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kibis.car.domain.User;
import ru.kibis.car.service.interfaces.UserService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user_create_servlet")
    public void createUser(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("city") String city
    ) {
        User user = new User(login,
                password,
                new Timestamp(System.currentTimeMillis()),
                name,
                email,
                city);
        this.userService.add(user);
    }

    @PostMapping(value = "/user_update_servlet", produces = "application/json")
    @ResponseBody
    public User updateUser(
            @RequestParam("id") int id,
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("city") String city
    ) {
        User user = this.userService.findById(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setCity(city);
        System.out.println(user.getLogin());
        return this.userService.update(user);
    }

    @GetMapping(value = "/user_update_servlet", produces = "application/json")
    @ResponseBody
    public User getUser(@RequestParam("id") int id) {
        return this.userService.findById(id);
    }

    @PostMapping(value = "/login", produces = "application/json")
    @ResponseBody
    public JSONObject logIn(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            HttpSession session
    ) {
        User user = this.userService.isCredentional(login, password);
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

    @PostMapping("/exit")
    public void logOut(HttpSession session) {
        session.invalidate();
    }
}