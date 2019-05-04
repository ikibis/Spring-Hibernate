package ru.kibis.car.service;

import ru.kibis.car.domain.User;

import java.util.List;

public interface UserService {
    User add(User user);

    User update(User user);

    void delete(int id);

    List<User> findAll();

    User findById(int id);

    User isCredentional(String login, String password);

}
