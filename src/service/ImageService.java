package service;

import jakarta.servlet.http.Part;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.PropertiesUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    private final String LOAD_IMAGE_PATH = PropertiesUtil.get("load.image.path");


    public static ImageService getInstance() {
        return INSTANCE;
    }

    public String getLoadImagePath() {
        return LOAD_IMAGE_PATH;
    }

    public void loadImage(Part part) {
        String fileName = part.getSubmittedFileName();
        Path path = Path.of(LOAD_IMAGE_PATH, fileName);
        try {
            Files.write(path, part.getInputStream().readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteImage(Path path) throws IOException {
        Files.delete(path);
    }

    public Optional<InputStream> get(String imagePath) throws IOException {
        Path path = Path.of(LOAD_IMAGE_PATH, imagePath);

        return Optional.of(Files.newInputStream(path));
    }
}
