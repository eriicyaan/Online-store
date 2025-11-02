package servlet;

import dto.GoodDto;
import dto.OrderDto;
import dto.UserDto;
import entity.User;
import exception.BalanceException;
import exception.UserNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BasketService;
import service.OrderService;
import service.UserService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;


@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();
    private final BasketService basketService = BasketService.getInstance();
    private final UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((UserDto) req.getSession().getAttribute("user")).getId();

        List<GoodDto> allOrders = orderService.getAllOrders(userId);

        req.setAttribute("orders", allOrders);
        req.getRequestDispatcher(JspHelper.getPath("orders")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int goodId = Integer.parseInt(req.getParameter("good_id"));
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        OrderDto orderDto = OrderDto.builder()
                .goodId(goodId)
                .userId(userDto.getId())
                .build();

        try {
            orderService.addOrder(userDto, orderDto);

            basketService.deleteGood(userDto.getId(), goodId);

            resp.sendRedirect("/basket?user_id=" + userDto.getId());

        } catch (BalanceException e) {
            resp.sendRedirect("/basket?user_id=" + userDto.getId() + "&error=1");
        }

    }

}
