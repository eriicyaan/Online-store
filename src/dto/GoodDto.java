package dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodDto {
    private int id;
    private String name;
    private String cost;
    private String imagePath;
}
