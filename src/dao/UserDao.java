package dao;

import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.UserMapper;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User>{
    private static final UserDao INSTANCE = new UserDao();

    private final UserMapper userMapper = UserMapper.getInstance();

    private final String SAVE_SQL = """
            INSERT INTO users(username, password, email, role, gender)
            VALUES (?, ?, ?, ?, ?);
            """;

    private final String FIND_BY_EMAIL_AND_PASSWORD_SQL= """
            SELECT * FROM users
            WHERE email = ? AND password = ?;
            """;
    private final String FIND_ALL_SQL = """
            SELECT *
            FROM users
            """;

    @Override
    public List<User> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();

            while(resultSet.next()) {
                users.add(userMapper.mapFrom(resultSet));
            }

            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {
    }

    @Override
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getRole().name());
            preparedStatement.setString(5, entity.getGender().name());

            int id = preparedStatement.executeUpdate();
            entity.setId(id);

            return entity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = null;

        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user = userMapper.mapFrom(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

}
