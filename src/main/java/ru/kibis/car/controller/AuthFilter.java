package ru.kibis.car.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Класс служит для аутентификации пользователя,
 * реализует интерфейс Filter
 */
public class AuthFilter implements Filter {

    /**
     * Метод, для фильтрации запросов пользователя,
     * проверяет залогинился ли пользователь в системе,
     * если не залогинился, то осуществляет редирект на страницу ввода логина и пароля
     *
     * @param req         HTTP запрос
     * @param resp        ответ
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/login")) {
            filterChain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
                return;
            }
            filterChain.doFilter(req, resp);
        }
    }
}
