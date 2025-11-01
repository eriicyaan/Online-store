package dao;

import entity.Good;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.GoodMapper;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodDao implements Dao<Integer, Good>{
    private static final GoodDao INSTANCE = new GoodDao();
    private final GoodMapper goodMapper = GoodMapper.getInstance();

    private final String SAVE_SQL = """
            INSERT INTO goods(name, cost, image_path)
            VALUES (?, ?, ?)
            """;

    private final String FIND_ALL_SQL = """
            SELECT *
            FROM goods;
            """;
    private final String DELETE_SQL= """
            DELETE FROM goods
            WHERE id = ?
            """;
    private final String FIND_BY_ID_SQL = """
            SELECT * FROM goods
            WHERE id = ?
            """;

    @Override
    public List<Good> findAll() {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Good> result = new ArrayList<>();

            while(resultSet.next()) {
                result.add(goodMapper.mapFrom(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Good> findById(Integer id) {
        try (Connection connection = ConnectionManager.get();
                 PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return Optional.ofNullable(goodMapper.mapFrom(resultSet));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer goodId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, goodId);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Good entity) {

    }

    @Override
    public Good save(Good entity) {
        try(Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getCost());
            preparedStatement.setString(3, entity.getImagePath());

            int id = preparedStatement.executeUpdate();

            entity.setId(id);

            return entity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static GoodDao getInstance() {
        return INSTANCE;
    }
}
