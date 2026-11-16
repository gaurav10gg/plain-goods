package com.plaingoods.web;

import com.plaingoods.dao.ProductDao;
import com.plaingoods.dao.PurchaseDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Admin page: add products, see all products and all purchases. */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private final ProductDao productDao = new ProductDao();
    private final PurchaseDao purchaseDao = new PurchaseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/login");
            return;
        }
        req.setAttribute("products", productDao.findAll());
        req.setAttribute("purchases", purchaseDao.findAll());
        req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
    }

    /** Add a new product. */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/login");
            return;
        }

        String name = req.getParameter("name").trim();
        String description = req.getParameter("description").trim();
        double price;
        try {
            price = Double.parseDouble(req.getParameter("price"));
        } catch (NumberFormatException e) {
            price = -1;
        }

        if (name.isEmpty() || price <= 0) {
            req.setAttribute("error", "Enter a name and a price greater than 0.");
            doGet(req, resp);
            return;
        }
        productDao.add(name, description, price);
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
