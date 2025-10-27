package servlet;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BasketService;

import java.io.IOException;

@WebServlet("/deleteGoodFromBasket")
public class DeleteGoodFromBasketServlet extends HttpServlet {
    private final BasketService basketService = BasketService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int good_id = Integer.parseInt(req.getParameter("good_id"));
        int user_id = ((User) req.getSession().getAttribute("user")).getId();

        basketService.deleteGood(user_id, good_id);
        resp.sendRedirect("/basket?user_id=" + user_id);
    }
}
