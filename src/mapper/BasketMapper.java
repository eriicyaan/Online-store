package mapper;

import dto.BasketDto;
import entity.Basket;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketMapper implements Mapper<BasketDto, Basket> {
    private static final BasketMapper INSTANCE = new BasketMapper();

    @Override
    public Basket mapFrom(BasketDto obj) {
        return Basket.builder()
                .user_id(obj.getUser_id())
                .good_id(obj.getGood_id())
                .build();
    }

    public static BasketMapper getInstance() {
        return INSTANCE;
    }

}
