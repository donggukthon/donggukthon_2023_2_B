package com.donggukthon.Showman.config.jwt;

import com.donggukthon.Showman.common.CustomException;
import com.donggukthon.Showman.common.Result;
import com.donggukthon.Showman.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    private Key key;

    /**
     * JWT의 Subject와 Claim으로 email 사용 -> 클레임의 name을 "email"으로 설정
     * JWT의 헤더에 들어오는 값 : 'Authorization(Key) = Bearer {토큰} (Value)' 형식
     */
    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String EMAIL_CLAIM = "email";
    private static final String BEARER = "Bearer ";

    private final UserRepository userRepository;


    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * AccessToken 생성 메소드
     */
    public String createAccessToken(Long userId) {
        long now = new Date().getTime();
        String accessToken = Jwts.builder()
                .claim("userId", userId)
                .setExpiration(new Date(now + accessTokenExpirationPeriod))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

    /**
     * 클라이언트 요청 헤더에서 AccessToken 추출
     * 토큰 형식 : Bearer XXX에서 Bearer를 제외하고 순수 토큰만 가져오기 위해서
     * 헤더를 가져온 후 "Bearer"를 삭제(""로 replace)
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(accessToken -> accessToken.startsWith(BEARER))
                .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    // "Bearer " 부분을 제거
    public String extractToken(String token){
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
        }catch (Exception e){
            throw new CustomException(Result.INVALID_TOKEN);
        }
        return token;
    }


    // 토큰으로 회원 아이디 정보 조회
    public Long getUserId(String token) {
        try {
            Long userId = Long.valueOf(String.valueOf(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId")));
            return userId;
        }catch (Exception e){
            throw new CustomException(Result.INVALID_TOKEN);
        }
    }

    // 토큰으로 회원 이메일 정보 조회
    public Optional<String> getUserEmail(String token) {
        String email = (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(EMAIL_CLAIM);
        return Optional.ofNullable(email);
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey) // 비밀키를 설정하여 파싱한다.
                    .parseClaimsJws(token);  // 주어진 토큰을 파싱하여 Claims 객체를 얻는다.
            log.info(claims.getBody().getExpiration().toString());
            // 토큰의 만료 시간과 현재 시간비교
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());  // 만료 시간이 현재 시간 이후인지 확인하여 유효성 검사 결과를 반환
        } catch (Exception e) {
            log.info("토큰 만료 = {}", token);
            return false;
        }
    }

    // Token 남은 유효시간
    public Long getExpiration(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        // 현재 시간
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

}