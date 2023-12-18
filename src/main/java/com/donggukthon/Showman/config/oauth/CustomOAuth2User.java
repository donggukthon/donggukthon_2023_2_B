package com.donggukthon.Showman.config.oauth;

import com.donggukthon.Showman.entity.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

//CustomOAuth2User를 구현하는 이유 -> Resource Server(카카오)에서 제공하지 않는 추가 정보들을 내 서비스에서 가지고 있기 위해
@Getter
public class CustomOAuth2User extends DefaultOAuth2User {
    /**
     * 처음 OAuth 로그인시 내 서비스에서 카카오 서버가 제공하지 않는 정보가 필요한 경우 내 서비스에서 사용자에게 직접 입력받아야함(사는곳, 번호 등등)
     * 이때 어떤 유저가 로그인한지 서버에서는 알 수 없기 때문에 OAuth 로그인 시 임의의 Email을 생성하여
     * AccessToken을 발급받아서 회원 식별용으로 AccessToken을 사용합니다.
    **/

    private String email;
    private Long userId;
    private UserRole userRole;
    /**
     * Constructs a {@code DefaultOAuth2User} using the provided parameters.
     *
     * @param authorities      the authorities granted to the user
     * @param attributes       the attributes about the user
     * @param nameAttributeKey the key used to access the user's &quot;name&quot; from
     *                         {@link #getAttributes()}
     */
    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            String email, UserRole userRole, Long userId) {
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.userRole = userRole;
        this.userId = userId;
    }
}
