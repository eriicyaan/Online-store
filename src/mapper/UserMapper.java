package mapper;


import dto.UserDto;
import entity.Gender;
import entity.Role;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<UserDto, User> {
    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public User mapFrom(UserDto obj) {
        return User.builder()
                .username(obj.getUsername())
                .email(obj.getEmail())
                .password(obj.getPassword())
                .role(Role.valueOf(obj.getRole()))
                .gender(Gender.valueOf(obj.getGender()))
                .balance(Double.parseDouble(obj.getBalance()))
                .build();
    }

    public UserDto mapTo(User obj) {
        return UserDto.builder()
                .username(obj.getUsername())
                .password(obj.getPassword())
                .email(obj.getEmail())
                .role(obj.getRole().name())
                .gender(obj.getGender().name())
                .balance("" + obj.getBalance())
                .build();
    }

    public User mapFrom(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getInt("id"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .email(resultSet.getString("email"))
                    .role(Role.find(resultSet.getString("role")).get())
                    .gender(Gender.find(resultSet.getString("gender")).get())
                    .balance(resultSet.getDouble("balance"))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
