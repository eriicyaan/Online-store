package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasketDto {
    private int user_id;
    private int good_id;
}
