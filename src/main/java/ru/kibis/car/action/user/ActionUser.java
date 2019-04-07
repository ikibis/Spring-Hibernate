package ru.kibis.car.action.user;

import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Интерфейс для действий пользователя
 */
public interface ActionUser {
    /**
     * Метод для обработки действия пользователя
     * @param validateService выбранный сервис валидации
     * @param req HTTP запрос
     */
    void doAction(ValidateServiceUser validateService, HttpServletRequest req);
}
