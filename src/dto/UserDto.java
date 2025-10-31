package dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String role;
    private String gender;
    private String balance;
}
