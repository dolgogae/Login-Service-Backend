package local.sihun.springsec.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
        HttpSession session = request.getSession();

        String accessToken = response.getHeader("Authorization");
        String refreshToken = response.getHeader("Refresh");

        log.info(accessToken);
        log.info(refreshToken);

        String redirectUrl = getRedirectUrl(accessToken, refreshToken);
        log.info(redirectUrl);

        response.sendRedirect(redirectUrl);
    }

    private String getRedirectUrl(String accessToken, String refreshToken){
        return "/auth/login/callback" +
                "?accessToken=" + accessToken +
                "&refreshToken=" + refreshToken;
    }
}