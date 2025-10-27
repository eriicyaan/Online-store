package validator;

import java.util.ArrayList;

public class ValidationResult {
    private final ArrayList<Error> errors = new ArrayList<>();


    public void add(Error error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }


    public ArrayList<Error> getErrors() {
        return errors;
    }
}
