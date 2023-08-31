package local.sihun.springsec.domain.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder @ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String password;
}
