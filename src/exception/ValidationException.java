package exception;

import validator.ValidationResult;
import validator.Error;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ValidationException extends Exception {
    private ArrayList<Error> errors;

    public ValidationException(ArrayList<Error> errors) {
        this.errors = errors;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }
}
