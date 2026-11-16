package com.plaingoods.web;

import com.plaingoods.dao.AdminDao;
import com.plaingoods.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Admin login, checked against MongoDB. */
@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {

    private final AdminDao adminDao = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            adminDao.createDefaultAdmin(); // in case MongoDB was started after the app
            Admin admin = adminDao.login(req.getParameter("username").trim(), req.getParameter("password"));
            if (admin != null) {
                req.getSession().setAttribute("admin", admin);
                resp.sendRedirect(req.getContextPath() + "/admin");
                return;
            }
            req.setAttribute("error", "Wrong username or password.");
        } catch (Exception e) {
            req.setAttribute("error", "Can't reach MongoDB. Is it running? (start-mongo.cmd)");
        }
        req.getRequestDispatcher("/WEB-INF/views/admin-login.jsp").forward(req, resp);
    }
}
