package ru.kibis.car.persistence;

import ru.kibis.car.model.user.User;

import java.sql.Timestamp;
import java.util.List;

public class UserStorage {
    private static final UserStorage INSTANCE = new UserStorage();
    private static final StorageWrapper WRAPPER = StorageWrapper.getINSTANCE();

    public static UserStorage getInstance() {
        return INSTANCE;
    }

    public User addUser(String login, String password, Timestamp createDate, String name, String email, String city) {
        return WRAPPER.tx(session -> {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setCreateDate(createDate);
            user.setName(name);
            user.setEmail(email);
            user.setCity(city);
            session.saveOrUpdate(user);
            return user;
        });
    }

    public User updateUser(User user, String login, String password, String name, String email, String city) {
        return WRAPPER.tx(session -> {
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
            user.setCity(city);
            session.saveOrUpdate(user);
            return user;
        });
    }

    public User deleteUser(User user) {
        return WRAPPER.tx(session -> {
            session.delete(user);
            return user;
        });
    }

    public List<User> findUsers() {
        return WRAPPER.tx(
                session -> session.createQuery("from User").list()
        );
    }

    public User findById(int id) {
        return WRAPPER.tx(
                session -> session.get(User.class, id)
        );
    }
}