package ru.kibis.car.service.interfaces;

import ru.kibis.car.domain.User;

import java.util.List;

public interface UserService {
    User add(User user);

    User update(User user);

    User findById(int id);

    User isCredentional(String login, String password);

}
