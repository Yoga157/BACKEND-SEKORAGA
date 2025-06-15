package com.skrg.sekoraga.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.repository.AdUserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private Key key;
    private Long tokenValidityInMilliseconds;
    private Long tokenValidityInMillisecondsForRememberMe;

    @Value("${spring.security.authentication.jwt.base64-secret}")
    private String base64Secret;

    @Value("${spring.security.authentication.jwt.token-validity-in-seconds}")
    private Long tokenValidityInSeconds;

    @Value("${spring.security.authentication.jwt.token-validity-in-seconds-for-remember-me}")
    private Long tokenValidityInSecondsForRememberMe;

    @Autowired
    private AdUserRepository adUserRepository;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        key = Keys.hmacShaKeyFor(keyBytes);
        tokenValidityInMilliseconds = 1000 * tokenValidityInSeconds;
        tokenValidityInMillisecondsForRememberMe = 1000 * tokenValidityInSecondsForRememberMe;
    }

    @Transactional
    public String createToken(Authentication authentication, Optional<AdUser> user, Boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        if (authorities.isEmpty()) {
            Optional<AdUser> rUser = adUserRepository.findById(user.get().getUserId());
            if (rUser.isPresent()) {
                authorities = rUser.get().getAuthorities().stream()
                        .map(a -> new SimpleGrantedAuthority(a.getName()).getAuthority())
                        .collect(Collectors.joining(","));
            }
        }

        Long now = new Date().getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + tokenValidityInMilliseconds);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .filter(auth -> auth != null && !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }

}
