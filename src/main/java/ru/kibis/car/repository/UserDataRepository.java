package ru.kibis.car.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kibis.car.model.user.User;

import java.util.List;
@Repository
public interface UserDataRepository extends CrudRepository<User, Integer> {
    List<User> findAll();

    User findById(int id);
}
