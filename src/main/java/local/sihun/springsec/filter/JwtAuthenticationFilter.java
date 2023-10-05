package local.sihun.springsec.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import local.sihun.springsec.global.config.AES128Config;
import local.sihun.springsec.domain.user.dto.UserDto;
import local.sihun.springsec.domain.user.service.UserService;
import local.sihun.springsec.redis.RedisUtils;
import local.sihun.springsec.security.CustomUserDetails;
import local.sihun.springsec.security.jwt.JwtTokenProvider;
import local.sihun.springsec.domain.auth.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AES128Config aes128Config;
    private final UserService userService;
    private final RedisUtils redisUtils;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // ServletInputStream을 LoginDto 객체로 역직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = objectMapper.readValue(request.getInputStream(), UserDto.class);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(customUserDetails);
        String accessToken = tokenDto.getAccessToken();
        String refreshToken = tokenDto.getRefreshToken();
        String encryptedRefreshToken = aes128Config.encryptAes(refreshToken);

        jwtTokenProvider.accessTokenSetHeader(accessToken, response);
        jwtTokenProvider.refresshTokenSetHeader(encryptedRefreshToken, response);

        UserDto findUser = userService
                .findUserAndUpdateTokens(customUserDetails.getId(), accessToken, refreshToken);

        // redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
        // 로그인 성공시 Refresh Token Redis 저장 ( key = Email / value = Refresh Token )
        long refreshTokenExpirationMillis = jwtTokenProvider.getRefreshTokenExpirationMillis();
        redisUtils.setData(findUser.getEmail(), refreshToken, refreshTokenExpirationMillis);

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }
}