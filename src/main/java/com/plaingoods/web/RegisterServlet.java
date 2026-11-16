package com.plaingoods.web;

import com.plaingoods.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String email = req.getParameter("email").trim().toLowerCase();
        String password = req.getParameter("password");

        if (name.isEmpty() || email.isEmpty() || password.length() < 6) {
            req.setAttribute("error", "Fill in all fields. Password needs at least 6 characters.");
        } else if (!userDao.register(name, email, password)) {
            req.setAttribute("error", "That email is already registered.");
        } else {
            req.getSession().setAttribute("user", userDao.login(email, password));
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }
}
