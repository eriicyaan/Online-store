package servlet;

import dto.GoodDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.GoodService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/goods")
public class GoodsServlet extends HttpServlet {
    private final GoodService goodService = GoodService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GoodDto> allGoods = goodService.getAllGoods();

        req.setAttribute("goods", allGoods);
        req.getRequestDispatcher(JspHelper.getPath("goods")).forward(req, resp);
    }

}
