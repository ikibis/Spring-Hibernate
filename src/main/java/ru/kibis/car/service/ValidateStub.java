package ru.kibis.car.service;



/**
 * Класс заглушка слоя валидации. Реализует интерфейс Validate.
 * Эмулирует запись в БД, пишет в HashMap.
 * Используется только для тестов.
 */
public class ValidateStub { /*implements Validate {
    *//**
     * HashMap для хранения записей
     *//*
    private final Map<Integer, User> store = new HashMap<>();
    *//**
     * Эмулирует id записи в БД.
     * счетчик id записей
     *//*
    private int ids = 0;

    *//**
     * Элемент паттерна синглтон
     *//*
    private static class Holder {
        private static final Validate INSTANCE = new ValidateStub();
    }
    *//**
     * Элемент паттерна синглтон
     *//*
    public static Validate getInstance() {
        return Holder.INSTANCE;
    }
    *//**
     * Метод добавления нового пользователя в хранилище
     *
     * @param user объект новый пользователь
     * @return true в случае удачного добавления и false если добавление не удалось выполнить
     *//*
    @Override
    public boolean add(User user) {
        boolean result = false;
        if (this.findByLogin(user.getLogin()) == null && this.findByEmail(user.getContacts().getEmail()) == null) {
            user.setId(this.ids++);
            this.store.put(user.getId(), user);
            result = true;
        }
        return result;
    }
    *//**
     * Метод изменения существующего пользователя в хранилище
     *
     * @param user        объект существующий пользователь
     * @param updatedUser объект пользователь с обновленными полями
     * @return true в случае удачного изменения и false если изменение не удалось выполнить
     *//*
    @Override
    public boolean update(User user, User updatedUser) {
        boolean result = false;
        if (!updatedUser.getContacts().getName().equals(user.getContacts().getName())) {
            user.getContacts().setName(updatedUser.getContacts().getName());
            result = true;
        }
        if (!updatedUser.getLogin().equals(user.getLogin()) && this.findByLogin(updatedUser.getLogin()) == null) {
            user.setLogin(updatedUser.getLogin());
            result = true;
        }
        if (!updatedUser.getContacts().getEmail().equals(user.getContacts().getEmail())
                && this.findByEmail(updatedUser.getContacts().getEmail()) == null) {
            user.getContacts().setEmail(updatedUser.getContacts().getEmail());
            result = true;
        }
        if (!updatedUser.getPassword().equals(user.getPassword())) {
            user.setPassword(updatedUser.getPassword());
            result = true;
        }
        if (!updatedUser.getRole().equals(user.getRole())) {
            user.setRole(updatedUser.getRole());
            result = true;
        }
        return result;
    }
    *//**
     * Метод удаления существующего пользователя из хранилища
     *
     * @param id айди пользователя
     *//*
    @Override
    public void delete(int id) {
        if (this.findById(id) != null) {
            this.store.remove(id);
        }
    }
    *//**
     * Метод для поиска всех пользователей
     *
     * @return Список объектов User
     *//*
    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.store.values());
    }

    public User findByLogin(String login) {
        User result = null;
        for (User userSearched : this.store.values()) {
            String userSearchedLogin = userSearched.getLogin();
            if (userSearchedLogin.equals(login)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }
    *//**
     * Метод для поиска всех стран
     *
     * @return Список стран
     *//*
    @Override
    public List<String> findCountries() {
        return null;
    }
    *//**
     * Метод для поиска всех городов для указанной страны
     *
     * @param country Страна
     * @return Список городов
     *//*
    @Override
    public List<String> findCities(String country) {
        return null;
    }
    *//**
     * Метод для поиска пользователя по email
     *
     * @param email пользователя
     * @return Объект User
     *//*
    public User findByEmail(String email) {
        User result = null;
        for (User userSearched : this.store.values()) {
            String userSearchedEmail = userSearched.getContacts().getEmail();
            if (userSearchedEmail.equals(email)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }

    *//**
     * Метод для поиска пользователя по id
     *
     * @param id пользователя
     * @return Объект User
     *//*
    public User findById(int id) {
        User result = null;
        for (User userSearched : this.store.values()) {
            int userSearchedId = userSearched.getId();
            if (userSearchedId == id) {
                result = userSearched;
                break;
            }
        }
        return result;
    }
    *//**
     * Метод для проверки залогинился ли пользователь в системе
     *
     * @param login Логин пользователя
     * @param password Пароль пользователя
     * @return объект User
     *//*
    @Override
    public User isCredentional(String login, String password) {
        return null;
    }
    *//**
     * Метод для тестирования сервлетов.
     * Исрользуется для очистки store HashMap, обнуляет счетчик id записей
     *//*
    public void clean() {
        this.store.clear();
        this.ids = 0;
    }*/
}
