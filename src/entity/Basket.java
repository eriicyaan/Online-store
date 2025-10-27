package entity;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class Basket {
    private int id;
    private int user_id;
    private int good_id;
}
