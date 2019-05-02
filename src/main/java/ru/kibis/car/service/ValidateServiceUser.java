package ru.kibis.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.kibis.car.model.user.User;
import ru.kibis.car.persistence.UserStorage;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ValidateServiceUser {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("spring-context.xml");
    private static final UserStorage USER_STORAGE = CONTEXT.getBean(UserStorage.class);

    @Autowired
    public ValidateServiceUser() {
    }

    /**
     * Метод добавления пользователя в хранилище
     *
     * @param login      логин пользователя
     * @param password   пароль пользователя
     * @param createDate дата создания пользователя
     * @param name       имя пользователя
     * @param email      адрес электронной почты пользователя
     * @param city       город пользователя
     * @return User объект пользователь
     */
    public User add(String login, String password, Timestamp createDate, String name, String email, String city) {
        User result = null;
        if (login != null
                && password != null
                && createDate != null
                && name != null
                && email != null
                && city != null) {
            result = USER_STORAGE.addUser(login, password, createDate, name, email, city);
        }
        return result;
    }

    public User update(User user, String login, String password, String name, String email, String city) {
        User result = null;
        if (user != null
                && login != null
                && password != null
                && name != null
                && email != null
                && city != null) {
            result = USER_STORAGE.updateUser(user, login, password, name, email, city);
        }
        return result;
    }

    public void delete(int id) {
        User user = this.findById(id);
        USER_STORAGE.deleteUser(user);
    }

    public List<User> findAll() {
        return USER_STORAGE.findUsers();
    }

    public User findById(int id) {
        return USER_STORAGE.findById(id);
    }

    public User isCredentional(String login, String password) {
        User result = null;
        for (User user : this.findAll()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                result = user;
                break;
            }
        }
        return result;
    }
}

