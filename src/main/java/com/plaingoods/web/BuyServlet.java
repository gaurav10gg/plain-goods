package com.plaingoods.web;

import com.plaingoods.dao.ProductDao;
import com.plaingoods.dao.PurchaseDao;
import com.plaingoods.model.Product;
import com.plaingoods.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** "Buy" button on a product. You must be logged in. */
@WebServlet("/buy")
public class BuyServlet extends HttpServlet {

    private final ProductDao productDao = new ProductDao();
    private final PurchaseDao purchaseDao = new PurchaseDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Product product = productDao.findById(Integer.parseInt(req.getParameter("productId")));
        if (product != null) {
            purchaseDao.buy(user.getId(), product.getId(), product.getPrice());
        }
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
