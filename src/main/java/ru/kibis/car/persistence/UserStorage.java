package ru.kibis.car.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.kibis.car.model.user.User;
import ru.kibis.car.repository.UserDataRepository;

import java.sql.Timestamp;
import java.util.List;

@Component
public class UserStorage {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("spring-context.xml");
    private static final UserDataRepository REPOSITORY = CONTEXT.getBean(UserDataRepository.class);

    @Autowired
    public UserStorage() {
    }

    public User addUser(String login, String password, Timestamp createDate, String name, String email, String city) {
      /*  User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setCreateDate(createDate);
        user.setName(name);
        user.setEmail(email);
        user.setCity(city);*/
        return REPOSITORY.save(new User (login,password, createDate, name, email, city));
        //return user;
    }

    public User updateUser(User user, String login, String password, String name, String email, String city) {
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setCity(city);
        REPOSITORY.save(user);
        return user;
    }

    public User deleteUser(User user) {
        REPOSITORY.delete(user);
        return user;
    }

    public List<User> findUsers() {
        return REPOSITORY.findAll();
    }

    public User findById(int id) {
        return REPOSITORY.findById(id);
    }
}