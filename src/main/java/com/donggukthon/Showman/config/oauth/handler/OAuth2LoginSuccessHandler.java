package com.donggukthon.Showman.config.oauth.handler;

import com.donggukthon.Showman.config.jwt.JwtService;
import com.donggukthon.Showman.config.oauth.CustomOAuth2User;
import com.donggukthon.Showman.entity.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);

            String targetUrl;

            // User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트
            if(oAuth2User.getUserRole() == UserRole.USER) {
                targetUrl = "http://218.52.120.43:3000/main"; //프론트에 맞게 변경
//                targetUrl = "http://localhost:8080/success"; //프론트에 맞게 변경

                String redirectUrl = createToken(response, oAuth2User, targetUrl);

                // 로그인 확인 페이지로 리다이렉트 시킨다.
                log.info("추가 회원가입 진행");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);

            }
        } catch (Exception e) {
            throw e;
        }
    }

    private String createToken(HttpServletResponse response, CustomOAuth2User oAuth2User, String targetUrl) throws IOException {
        String accessToken = jwtService.createAccessToken(oAuth2User.getUserId());
        log.info(accessToken);

        String redirectUrl = UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("accessToken", accessToken)
                .build().encode(StandardCharsets.UTF_8).toUriString();
        return redirectUrl;
    }
}
