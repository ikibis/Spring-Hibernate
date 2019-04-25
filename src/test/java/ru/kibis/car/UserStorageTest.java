package ru.kibis.car;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import ru.kibis.car.model.user.User;
import ru.kibis.car.persistence.UserStorage;

import java.sql.Timestamp;

public class UserStorageTest {
    UserStorage userStorage;

    @Before
    public void beforeTests() {
        userStorage = UserStorage.getInstance();
    }

    @Test
    public void whenAddUserThenStore() {
        User user = this.userStorage.addUser(
                "new",
                "pass",
                new Timestamp(System.currentTimeMillis()),
                "Mark",
                "email@email",
                "Ekb"
        );
        assertThat(user.getId(), is(this.userStorage.findById(user.getId()).getId()));
    }

    @Test
    public void whenUpdateUserThenStore() {
        User user = this.userStorage.addUser(
                "new",
                "pass",
                new Timestamp(System.currentTimeMillis()),
                "Mark",
                "email@email",
                "Ekb"
        );
        this.userStorage.updateUser(
                user,
                "new",
                "pass",
                "Updated_Mark",
                "email@email",
                "Ekb"
        );
        assertThat(user.getName(), is(this.userStorage.findById(user.getId()).getName()));
    }

    @Test
    public void whenDeleteUser() {
        User user = this.userStorage.addUser(
                "new",
                "pass",
                new Timestamp(System.currentTimeMillis()),
                "Mark",
                "email@email",
                "Ekb"
        );
        this.userStorage.deleteUser(user);
        assertNull(this.userStorage.findById(user.getId()));
    }

    @Test
    public void whenAddUserThenFindById() {
        User user = this.userStorage.addUser(
                "new",
                "pass",
                new Timestamp(System.currentTimeMillis()),
                "Mark",
                "email@email",
                "Ekb"
        );
        assertThat(user.getLogin(), is(this.userStorage.findById(user.getId()).getLogin()));
    }


}
