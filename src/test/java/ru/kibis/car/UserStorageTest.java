//package ru.kibis.car;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//
//import ru.kibis.car.model.user.User;
//import ru.kibis.car.persistence.UserStorage;
//
//import java.sql.Timestamp;
//import java.util.LinkedList;
//import java.util.List;
//
//public class UserStorageTest {
//    private UserStorage userStorage;
//
//   /* @Before
//    public void beforeTests() {
//        userStorage = UserStorage.getInstance();
//    }*/
//
//    @After
//    public void afterTests() {
//        List<User> users = this.userStorage.findUsers();
//        for (User user : users) {
//            this.userStorage.deleteUser(user);
//        }
//    }
//
//    @Test
//    public void whenAddUsersThenFind() {
//        List<User> testList = new LinkedList<>();
//        User userFirst = this.userStorage.addUser(
//                "new1",
//                "pass1",
//                new Timestamp(System.currentTimeMillis()),
//                "Mark1",
//                "email@email1",
//                "Ekb1"
//        );
//        testList.add(userFirst);
//        User userSecond = this.userStorage.addUser(
//                "new2",
//                "pass2",
//                new Timestamp(System.currentTimeMillis()),
//                "Mark2",
//                "email@email2",
//                "Ekb2"
//        );
//        testList.add(userSecond);
//        List<User> listFromDb = this.userStorage.findUsers();
//        assertThat(testList.get(0).getLogin(), is(listFromDb.get(0).getLogin()));
//        assertThat(testList.get(1).getId(), is(listFromDb.get(1).getId()));
//    }
//
//    @Test
//    public void whenAddUserThenStore() {
//        User user = this.userStorage.addUser(
//                "new",
//                "pass",
//                new Timestamp(System.currentTimeMillis()),
//                "Mark",
//                "email@email",
//                "Ekb"
//        );
//        assertThat(user.getId(), is(this.userStorage.findById(user.getId()).getId()));
//    }
//
//
//    @Test
//    public void whenUpdateUserThenStore() {
//        User user = this.userStorage.addUser(
//                "new2",
//                "pass2",
//                new Timestamp(System.currentTimeMillis()),
//                "Mark2",
//                "email@email2",
//                "Ekb2"
//        );
//        this.userStorage.updateUser(
//                user,
//                "new2",
//                "pass2",
//                "Updated_Mark2",
//                "email@email2",
//                "Ekb2"
//        );
//        assertThat(user.getName(), is(this.userStorage.findById(user.getId()).getName()));
//    }
//
//    @Test
//    public void whenDeleteUser() {
//        User user = this.userStorage.addUser(
//                "new",
//                "pass",
//                new Timestamp(System.currentTimeMillis()),
//                "Mark",
//                "email@email",
//                "Ekb"
//        );
//        this.userStorage.deleteUser(user);
//        assertNull(this.userStorage.findById(user.getId()));
//    }
//
//    @Test
//    public void whenAddUserThenFindById() {
//        User user = this.userStorage.addUser(
//                "new",
//                "pass",
//                new Timestamp(System.currentTimeMillis()),
//                "Mark",
//                "email@email",
//                "Ekb"
//        );
//        assertThat(user.getLogin(), is(this.userStorage.findById(user.getId()).getLogin()));
//    }
//}
