package local.sihun.springsec.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "사용자 요청 DTO")
public class UserRequestDto {

    @NotBlank
    @Size(min = 2, message = "Username length must be greater than 2.")
    @Schema(description = "사용자 이름", example = "홍길동")
    private String username;

    @NotBlank
    @Email(message = "Not Valid Email")
    @Schema(description = "사용자 email", example = "abc@gmail.com")
    private String email;

    @NotBlank
    @Size(min = 2, message = "Password length must be greater than 2.")
    @Schema(description = "사용자 비밀번호", example = "비밀번호")
    private String password;
}
