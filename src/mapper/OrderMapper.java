package mapper;

import dto.OrderDto;
import entity.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper implements Mapper<OrderDto, Order>{
    private static final OrderMapper INSTANCE = new OrderMapper();

    @Override
    public Order mapFrom(OrderDto obj) {
        return Order.builder()
                .id(obj.getId())
                .userId(obj.getUserId())
                .goodId(obj.getGoodId())
                .build();
    }


    public static OrderMapper getInstance() {
        return INSTANCE;
    }
}
