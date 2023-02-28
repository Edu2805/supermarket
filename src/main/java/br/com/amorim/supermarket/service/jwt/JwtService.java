package br.com.amorim.supermarket.service.jwt;

import br.com.amorim.supermarket.model.userdata.UserData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.key-signature}")
    private String signatureKey;

    public String generateToken(UserData userData) {
        long expString = Long.parseLong(expiration);
        LocalDateTime expiryDateTime = LocalDateTime
                .now()
                .plusMinutes(expString);
        Instant instant = expiryDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return Jwts.builder()
                .setSubject(userData.getUserName())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime date = expirationDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return !LocalDateTime.now().isAfter(date);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserLogin(String token) throws ExpiredJwtException {
        return getClaims(token).getSubject();
    }
}
