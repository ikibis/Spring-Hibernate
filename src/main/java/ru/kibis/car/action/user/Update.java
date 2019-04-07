package ru.kibis.car.action.user;

import ru.kibis.car.model.user.User;
import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * Класс реализует интерфейс ActionAd для изменения существующего пользователя
 */
public class Update implements ActionUser {
    /**
     * Метод извлекает из HTTP запроса id пользователя,
     * через сервис валидации находит пользователя user по данному id,
     * создает новый объект updatedUser,
     * передает user и updatedUser в сервис валидации, для проверки и последующего изменения в методе update
     *
     * @param validateService выбранный сервис валидации
     * @param req             HTTP запрос
     */
    @Override
    public void doAction(ValidateServiceUser validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        int idToUpdate = Integer.valueOf(Objects.requireNonNull(map.get("id")[0]));
        User user = validateService.findById(idToUpdate);
        validateService.update(
                user,
                map.get("login")[0],
                map.get("password")[0],
                map.get("name")[0],
                map.get("email")[0],
                map.get("city")[0]
        );
    }
}
