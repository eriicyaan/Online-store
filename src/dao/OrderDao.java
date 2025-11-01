package dao;

import entity.Good;
import entity.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.GoodMapper;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDao implements Dao<Integer, Order>{
    private static final OrderDao INSTANCE = new OrderDao();

    private final GoodMapper goodMapper = GoodMapper.getInstance();

    private final String SAVE_SQL = """
            INSERT INTO orders(user_id, good_id)
            VALUES (?, ?)
            """;

    private final String FIND_ALL_WITH_JOIN_SQL = """
            SELECT g.id, g.name, g.cost, g.image_path
            FROM orders o
            JOIN goods g
                ON o.good_id = g.id
            JOIN users u
                ON o.user_id = u.id
            WHERE u.id = ?
            """;

    @Override
    public Order save(Order entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getGoodId());

            int id = preparedStatement.executeUpdate();

            entity.setId(id);

            return entity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Good> findAllOrdersWithJoin(int userId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_WITH_JOIN_SQL)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Good> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(goodMapper.mapFrom(resultSet));
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Order entity) {

    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
