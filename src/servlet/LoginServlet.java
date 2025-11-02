package servlet;

import dto.UserDto;
import entity.User;
import exception.UserNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import util.JspHelper;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            UserDto user = userService.loginUser(req.getParameter("email"), req.getParameter("password"));
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("role", user.getRole());
            resp.sendRedirect("/account");

        } catch (UserNotFoundException e) {
            resp.sendRedirect("/login?error=1");
        }
    }
}
