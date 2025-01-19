package com.root.sms.auth_service.helpers;

import com.root.sms.auth_service.config.ConsulConfig;
import com.root.sms.auth_service.vo.MemberVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTHelper {


    public static final int JWT_DEFAULT_TIMEOUT = 20;
    private final ConsulConfig config;

    @Autowired
    public JWTHelper(ConsulConfig config){
        this.config = config;
    }

    public String getJwtToken(MemberVO memberVO){
        String secret = config.getConfigValueByKey("JWT_SECRET");
        int timeout = config.getConfigValueByKey("JWT_TIMEOUT", JWT_DEFAULT_TIMEOUT);
        return createJWTToken(secret, timeout, memberVO);
    }

    private String createJWTToken(String secret, int timeout, MemberVO memberVO){

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        return Jwts.builder()
                .claim("firstName", memberVO.getFirstName())
                .claim("lastName", memberVO.getLastName())
                .claim("email", memberVO.getEmail())
                .setSubject("login")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(timeout, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();
    }

}
