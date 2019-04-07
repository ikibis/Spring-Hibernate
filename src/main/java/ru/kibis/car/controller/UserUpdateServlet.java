package ru.kibis.car.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ru.kibis.car.action.user.ActionFactoryUser;
import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для изменения пользователя
 */
public class UserUpdateServlet extends HttpServlet {
    /**
     * Сервис валидации
     */
    private final ValidateServiceUser validateService = ValidateServiceUser.getInstance();
    /**
     * Фабрика для действий пользователя
     */
    private ActionFactoryUser factory = ActionFactoryUser.getInstance();

    /**
     * Метод GET извлекает из параметров HTTP запроса "id",
     * с помощью сервиса валидации ищет пользователя с таким "id".
     * Если пользователь существует, то запрашиваются атрибуты сессии "login" и "role".
     * И происходит проверка наличия прав на осуществление операции изменения.
     * Если прав хватает, объект User в качестве атрибута передается на JSP страницу редактирования пользователя.
     * Если прав не хватает, то в атрибут запроса передается сообщение об ошибке и
     * происходит переход на основной сервлет.
     *
     * @param req  HTTP запрос
     * @param resp ответ
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validateService.findById(id);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(user);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }



    /**
     * Метод POST вызывает метод action фабрки для изменения пользователя,
     * осуществляет редирект на основной сервлет.
     *
     * @param req  HTTP запрос
     * @param resp ответ
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        factory.action("update", req);
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath() + "/servlets");
    }
}

