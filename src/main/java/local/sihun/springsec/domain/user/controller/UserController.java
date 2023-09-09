package local.sihun.springsec.domain.user.controller;

import io.jsonwebtoken.Claims;
import local.sihun.springsec.domain.user.UserMappingProvider;
import local.sihun.springsec.domain.user.dto.UserDto;
import local.sihun.springsec.domain.user.dto.UserResponseDto;
import local.sihun.springsec.domain.user.service.UserService;
import local.sihun.springsec.global.result.ResultCode;
import local.sihun.springsec.global.result.ResultResponse;
import local.sihun.springsec.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMappingProvider userMappingProvider;

    @GetMapping("/get")
    public ResponseEntity<ResultResponse> getUser(
            @RequestHeader String token
    ){
        Claims claims = jwtTokenProvider.validateAndParseToken(token);
        String email = (String) claims.get("email");

        UserDto user = userService.getUser(email);
        UserResponseDto responseDto = userMappingProvider.userDtoToResponseDto(user);

        ResultResponse result = ResultResponse.of(ResultCode.GET_MY_INFO_SUCCESS, responseDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }
}
