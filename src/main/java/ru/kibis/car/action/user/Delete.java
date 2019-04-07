package ru.kibis.car.action.user;

import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * Класс реализует интерфейс ActionAd для удаления пользователя
 */
public class Delete implements ActionUser {
    /**
     * Метод извлекает из HTTP запроса id пользователя,
     * передает его в сервис валидации, для последующего удаления в методе delete
     * @param validateService выбранный сервис валидации
     * @param req HTTP запрос
     */
    @Override
    public void doAction(ValidateServiceUser validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        int idToDelete = Integer.valueOf(Objects.requireNonNull(map.get("id")[0]));
        if (validateService.findById(idToDelete) != null) {
            validateService.delete(idToDelete);
        }
    }
}
