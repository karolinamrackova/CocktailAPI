package project.week0project.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "IwpAHjdJ4Rm/ZLdePZaaAM0mWZVBPMwmEtIV7X18OZJ1TZnI0UZr1KFpWnW+NOOqcw1jCVye2X8wXIH82UPgrfob0WpNNAgXEnlL9LIZTZ5Y6V8sMOSnLvIkGrKlpS2AmIS/UQGq3GasoBw65ky0TLNyIavM2klMBNtyAbrY4D0gstse4ZxibXXYNh75zHeNcIqZI573ce+Aa17A/h6lCzMM2wpe5+tx6uARU2zMuYlbKKWgq/6cmSLpk9oL65XllK8nVl4LwWHuCIloATY+NbOCaHZ72zfvsGgnDkSb4uB2oySLsOG1ouZZBMyJ+xsIo7xO/LemjF42qCAB9ZCKIEjRkXKYixgQkiuj5tYysZg=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
}
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
    }


}
