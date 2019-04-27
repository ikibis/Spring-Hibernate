package ru.kibis.car.controller;

import ru.kibis.car.action.user.ActionFactoryUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для создания нового пользователя
 */
public class UserCreateServlet extends HttpServlet {
    /**
     * Фабрика для действий пользователя
     */
    private final ActionFactoryUser factory = ActionFactoryUser.getInstance();

    /**
     * Метод POST вызывает метод action фабрки для создания нового пользователя,
     * осуществляет редирект на основной сервлет
     *
     * @param req  HTTP запрос
     * @param resp ответ
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        factory.action("create", req);
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath() + "/");
    }
}