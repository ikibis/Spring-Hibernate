package ru.kibis.car.action.user;

import ru.kibis.car.service.ValidateServiceUser;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика для обработки действий пользователя
 */
public class ActionFactoryUser {
    private static class Holder {
        private static final ActionFactoryUser INSTANCE = new ActionFactoryUser();
    }

    /**
     * Метод возвращает новую фабрику действий пользователя
     *
     * @return Фабрика действий пользователя
     */
    public static ActionFactoryUser getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Сервис валидации
     */
    private ValidateServiceUser service = ValidateServiceUser.getInstance();

    /**
     * HashMap для хранения действий пользователя
     */
    private Map<String, ActionUser> actionMap = new HashMap<>();

    {
        actionMap.put("create", new Create());
        actionMap.put("update", new Update());
        actionMap.put("delete", new Delete());
    }

    /**
     * Метод проверяет переданное значение для действия пользователя
     * и вызывает для него doAction, если такое действие существует
     *
     * @param action действие пользователя
     * @param req    Http запрос
     */
    public void action(String action, HttpServletRequest req) {
        actionMap.getOrDefault(action, new UnknownAction()).doAction(service, req);
    }

    /**
     * Класс для возможной реализации выброса исключении, при неизвестном действии пользователя
     */
    class UnknownAction implements ActionUser {
        /**
         * Метод для обработки действия пользователя
         *
         * @param validateService выбранный сервис валидации
         * @param req             HTTP запрос
         */
        @Override
        public void doAction(ValidateServiceUser validateService, HttpServletRequest req) {
        }

    }
}
