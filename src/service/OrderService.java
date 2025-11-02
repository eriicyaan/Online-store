package service;


import dao.OrderDao;
import dto.GoodDto;
import dto.OrderDto;
import dto.UserDto;
import entity.Good;
import entity.User;
import exception.BalanceException;
import exception.GoodNotFoundException;
import exception.UserNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.GoodMapper;
import mapper.OrderMapper;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderService {
    private static final OrderService INSTANCE = new OrderService();

    private final OrderDao orderDao = OrderDao.getInstance();
    private final OrderMapper orderMapper = OrderMapper.getInstance();
    private final GoodMapper goodMapper = GoodMapper.getInstance();
    private final UserService userService = UserService.getInstance();
    private final GoodService goodService = GoodService.getInstance();

    public List<GoodDto> getAllOrders(int userId) {
        return orderDao.findAllOrdersWithJoin(userId).stream()
                .map(goodMapper::mapTo).toList();
    }

    public void addOrder(UserDto userDto, OrderDto order) throws BalanceException {
        try {
            double userBalance = userService.getBalance(order.getUserId());
            double goodCost = goodService.getCost(order.getGoodId());

            if(userBalance - goodCost < 0) {
                throw new BalanceException("The balance lesser then cost of product");
            }

            orderDao.save(orderMapper.mapFrom(order));
            userDto.setBalance("" + (userBalance - goodCost));

            userService.updateBalance(order.getUserId(), userDto);

        } catch (UserNotFoundException | GoodNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static OrderService getInstance() {
        return INSTANCE;
    }
}
