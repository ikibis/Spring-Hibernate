package ru.kibis.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kibis.car.domain.User;
import ru.kibis.car.repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User add(User user) {
        return this.repository.save(user);
    }

    @Override
    public User update(User user) {
        return this.repository.save(user);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return this.repository.findAll();
    }

    @Override
    public User findById(int id) {
        return this.repository.findById(id);
    }

    @Override
    public User isCredentional(String login, String password) {
        return this.repository.findAll().stream()
                .filter(user -> login.equals(user.getLogin()))
                .filter(user -> password.equals(user.getPassword()))
                .findFirst().orElse(null);
    }
}