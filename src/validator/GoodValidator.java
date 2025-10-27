package validator;

import dto.GoodDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodValidator implements Validator<GoodDto, ValidationResult>{
    private static final GoodValidator INSTANCE = new GoodValidator();

    @Override
    public ValidationResult isValid(GoodDto obj) {
        ValidationResult validationResult = new ValidationResult();

        if(obj.getName() == null || obj.getCost() == null) {
            validationResult.add(Error.builder().code("Param error").description("Name of cost is null").build());
        }
        if(obj.getCost() != null && Double.parseDouble(obj.getCost()) < 0) {
            validationResult.add(Error.builder().code("Negative cost").description("The cost is negative").build());
        }

        return validationResult;
    }


    public static GoodValidator getInstance() {
        return INSTANCE;
    }
}
