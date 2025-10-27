package entity;

import java.util.Optional;
import java.util.stream.Stream;

public enum Gender {
    MALE,
    FEMALE;

    public static Optional<Gender> find(String gender) {
        return Stream.of(Gender.values())
                .filter(gend -> gender.equals(gend.name()))
                .findFirst();
    }
}
