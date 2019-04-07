package ru.kibis.car.action.user;

import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Класс реализует интерфейс ActionUser для создания пользователя
 */
public class Create implements ActionUser {

    /**
     * Метод извлекает из HTTP запроса параметры необходимые для создания объекта User,
     * передает его в сервис валидации, для проверки и последующего добавления в методе add
     *
     * @param validateService выбранный сервис валидации
     * @param req             HTTP запрос
     */
    @Override
    public void doAction(ValidateServiceUser validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        validateService.add(
                map.get("login")[0],
                map.get("password")[0],
                new Timestamp(System.currentTimeMillis()),
                map.get("name")[0],
                map.get("email")[0],
                map.get("city")[0]
        );
    }
}


