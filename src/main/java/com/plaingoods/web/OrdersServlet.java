package com.plaingoods.web;

import com.plaingoods.dao.PurchaseDao;
import com.plaingoods.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** The logged-in user's purchases. */
@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    private final PurchaseDao purchaseDao = new PurchaseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.setAttribute("orders", purchaseDao.findByUser(user.getId()));
        req.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(req, resp);
    }
}
