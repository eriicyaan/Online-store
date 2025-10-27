package service;


import dao.UserDao;
import dto.UserDto;
import entity.User;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.UserMapper;
import validator.UsersValidator;
import validator.ValidationResult;

import java.sql.SQLException;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();

    private final UsersValidator validator = UsersValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final UserMapper mapper = UserMapper.getInstance();

    public void registerUser(UserDto user) throws ValidationException {
        ValidationResult result = validator.isValid(user);

        if(result.isValid()) {
            userDao.save(mapper.mapFrom(user));
        } else {
            throw new ValidationException(result.getErrors());
        }
    }

    public Optional<User> loginUser(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
