package local.sihun.springsec.domain.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import local.sihun.springsec.domain.user.UserMappingProvider;
import local.sihun.springsec.domain.user.dto.UserDto;
import local.sihun.springsec.domain.user.dto.UserRequestDto;
import local.sihun.springsec.domain.user.dto.UserResponseDto;
import local.sihun.springsec.domain.user.service.UserService;
import local.sihun.springsec.global.result.ResultCode;
import local.sihun.springsec.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserMappingProvider userMappingProvider;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "회원가입(sign-in)", description = "회원가입을 위한 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = UserRequestDto.class)))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<ResultResponse> signIn(
            @RequestBody UserRequestDto request
    ){
        log.info(request.toString());

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserDto userDto = userMappingProvider.requestDtoToDto(request);
        UserDto user = userService.createUser(userDto);

        log.info("create user = {}", user.toString());

        UserResponseDto userResponseDto = userMappingProvider.userDtoToResponseDto(user);

        ResultResponse result = ResultResponse.of(ResultCode.REGISTER_SUCCESS, userResponseDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @Operation(summary = "JWT 로그인 성공 콜백 함수", description = "JWT 로그인 이후 성공 콜백 함수 - 현재 front 미구현으로 구현중인 상태")
    @GetMapping("/login/callback")
    public String loginCallback(
            @RequestParam String accessToken, @RequestParam String refreshToken
    ){
        return refreshToken;
    }
}
