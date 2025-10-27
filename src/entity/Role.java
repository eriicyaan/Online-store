package entity;

import java.util.Optional;
import java.util.stream.Stream;

public enum Role {
    USER,
    ADMIN;

    public static Optional<Role> find(String role) {
        return Stream.of(Role.values())
                .filter(rol -> role.equals(rol.name()))
                .findFirst();
    }
}
