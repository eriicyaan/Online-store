package servlet;

import dto.BasketDto;
import dto.GoodDto;
import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BasketService;
import util.JspHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
    private final BasketService basketService = BasketService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("user_id");

        try {
            List<GoodDto> userGoods = basketService.getAllGoods(Integer.parseInt(userId));

            req.setAttribute("userGoods", userGoods);
            req.getRequestDispatcher(JspHelper.getPath("basket")).forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int good_id = Integer.parseInt(req.getParameter("good_id"));
        int user_id = ((UserDto) req.getSession().getAttribute("user")).getId();

        BasketDto basket = BasketDto.builder()
                .user_id(user_id)
                .good_id(good_id)
                .build();

        basketService.addGood(basket);
        resp.sendRedirect("/goods");
    }
}
