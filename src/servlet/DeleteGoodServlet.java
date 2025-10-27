package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DeleteGoodService;
import service.ImageService;

import java.io.IOException;
import java.nio.file.Path;

@WebServlet("/deleteGoodServlet")
public class DeleteGoodServlet extends HttpServlet {
    private final DeleteGoodService deleteGoodService = DeleteGoodService.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int goodId = Integer.parseInt(req.getParameter("good_id"));
        String imagePath = req.getParameter("image_path");
        Path path = Path.of(imageService.getLoadImagePath(), imagePath);

        deleteGoodService.deleteGood(goodId);
        imageService.deleteImage(path);

        resp.sendRedirect("/goods");
    }
}
