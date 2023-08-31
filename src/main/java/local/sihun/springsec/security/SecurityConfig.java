package local.sihun.springsec.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

//    private final JwtTokenProvicer jwtTokenProvicer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return null;
    }
}
