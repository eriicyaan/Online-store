package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = req.getParameter("path");
        Optional<InputStream> hasInputStream = imageService.get(imagePath);

        if(hasInputStream.isPresent()) {

            try(InputStream inputStream = hasInputStream.get();
                ServletOutputStream outputStream = resp.getOutputStream()) {

                int num;

                while((num = inputStream.read()) != -1) {
                    outputStream.write(num);
                }
            }
        }
    }
}
