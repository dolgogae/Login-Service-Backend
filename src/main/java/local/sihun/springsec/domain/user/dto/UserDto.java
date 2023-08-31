package local.sihun.springsec.domain.user.dto;

import lombok.*;
@Data
@Builder @ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String password;
    private String role;
}
