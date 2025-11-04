package service;


import dao.UserDao;
import dto.UserDto;
import entity.User;
import exception.UserNotFoundException;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.UserMapper;
import validator.UserValidator;
import validator.ValidationResult;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();

    private final UserValidator validator = UserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public void registerUser(UserDto user) throws ValidationException {
        ValidationResult result = validator.isValid(user);

        if(result.isValid()) {
            userDao.save(userMapper.mapFrom(user));
        } else {
            throw new ValidationException(result.getErrors());
        }
    }

    public UserDto loginUser(String email, String password) throws UserNotFoundException {
        Optional<User> user = userDao.findByEmailAndPassword(email, password);

        if(user.isPresent()) {
            return userMapper.mapTo(user.get());
        }
        throw new UserNotFoundException();
    }

    public List<UserDto> getListOfUsers() {
        return userDao.findAll().stream()
                .map(userMapper::mapTo)
                .toList();
    }

//    public double getBalance(int userId) throws UserNotFoundException {
//        Optional<User> maybeUser = userDao.findById(userId);
//
//        if(maybeUser.isPresent()) {
//            return maybeUser.get().getBalance();
//        }
//        throw new UserNotFoundException();
//    }

    public void setBalance(UserDto userDto, double amountFromUser) {
        if(amountFromUser < 0) {
            throw new IllegalArgumentException("amount is negative");
        }

        double currentBalance = Double.parseDouble(userDto.getBalance());
        double newBalance = currentBalance + amountFromUser;

        try {
            userDto.setBalance(String.valueOf(newBalance));
            updateUser(userDto.getId(), userDto);
        } catch (RuntimeException e) {
            userDto.setBalance(String.valueOf(currentBalance));
        }
    }

    public void updateUser(int userId, UserDto newUser) {
        User user = userMapper.mapFrom(newUser);
        user.setId(userId);
        userDao.update(userMapper.mapFrom(newUser));
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
