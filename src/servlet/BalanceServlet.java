package servlet;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;


@WebServlet("/balance")
public class BalanceServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto)  req.getSession().getAttribute("user");
        String amountParam = req.getParameter("amount");

        if(amountParam == null || amountParam.isEmpty()) {
            resp.sendRedirect("/account?error=1");
        }
        else {
            double amountFromUser = Double.parseDouble(amountParam);

            try {
                userService.setBalance(userDto, amountFromUser);
                resp.sendRedirect("/account");
            } catch (IllegalArgumentException e) {
                resp.sendRedirect("/account?error=2");
            }
        }
    }
}
