package validator;

import dto.UserDto;
import entity.Gender;
import entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserValidator implements Validator<UserDto, ValidationResult> {
    private static final UserValidator INSTANCE = new UserValidator();

    @Override
    public ValidationResult isValid(UserDto obj) {
        ValidationResult validationResult = new ValidationResult();

        if(Gender.find(obj.getGender()).isEmpty()) {
            validationResult.add(Error.builder().code("gender error").code("Gender is not valid").build());
        }
        if(Role.find(obj.getRole()).isEmpty()) {
            validationResult.add(Error.builder().code("role error").description("Role is not valid").build());
        }
        if(obj.getUsername() == null || obj.getPassword() == null || obj.getEmail() == null ||
                obj.getGender() == null || obj.getRole() == null) {
            validationResult.add(Error.builder().code("empty error").description("There are empty parameters").build());
        }

        return validationResult;
    }

    public static UserValidator getInstance() {
        return INSTANCE;
    }
}
