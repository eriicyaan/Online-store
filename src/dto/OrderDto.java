package dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderDto {
    private int id;
    private int userId;
    private int goodId;
}
