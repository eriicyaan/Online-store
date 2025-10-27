package servlet;

import dao.GoodDao;
import dto.GoodDto;
import entity.Good;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.GoodService;
import service.ImageService;
import util.JspHelper;

import java.io.IOException;

@MultipartConfig
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final GoodService goodService = GoodService.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("admin")).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String cost = req.getParameter("cost");
        Part image = req.getPart("image");

        GoodDto good = GoodDto.builder()
                .name(name)
                .cost(cost)
                .imagePath(image.getSubmittedFileName())
                .build();

        try {
            goodService.addGood(good, req);
            resp.sendRedirect("/account");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
