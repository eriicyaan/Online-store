package servlet;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class ListOfUsersServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> listOfUsers = userService.getListOfUsers();

        req.setAttribute("users", listOfUsers);
        req.getRequestDispatcher(JspHelper.getPath("users")).forward(req, resp);
    }
}
