package dao;

import entity.Basket;
import entity.Good;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.GoodMapper;
import util.ConnectionManager;

import javax.xml.parsers.SAXParser;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketDao implements Dao<Integer, Basket>{
    private static final BasketDao INSTANCE = new BasketDao();
    private final GoodMapper mapper = GoodMapper.getInstance();

    private final String GET_ALL_ADDED_GOODS_SQL = """
            SELECT g.id, g.name, g.cost, g.image_path
            FROM basket b
            JOIN users u
            ON b.user_id = u.id
            JOIN goods g
            ON b.good_id = g.id
            WHERE u.id = ?
            """;

    private final String SAVE_SQL = """
            INSERT INTO basket(user_id, good_id)
            VALUES (?, ?)
            """;

//    private final String DELETE_SQL = """
//            DELETE FROM basket
//            WHERE user_id = ? AND good_id = ?
//
//            """;
    private final String DELETE_SQL = """
        DELETE FROM basket
        WHERE id IN (SELECT id FROM basket WHERE user_id = ? AND good_id = ? LIMIT 1);
        """;

    @Override
    public Basket save(Basket entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, entity.getUser_id());
            preparedStatement.setInt(2, entity.getGood_id());
            int id = preparedStatement.executeUpdate();

            entity.setId(id);
            return entity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Good> getAllAddedGoods(int id) throws SQLException {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ADDED_GOODS_SQL)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Good> result = new ArrayList<>();

            while(resultSet.next()) {
                result.add(mapper.mapFrom(resultSet));
            }

            return result;
        }
    }


    public static BasketDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Basket> findAll() {
        return List.of();
    }

    @Override
    public Optional<Basket> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public void delete(int userId, int goodId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Basket entity) {

    }
}
