package com.k35dl.g6.config;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
    // phương thức này đòi hỏi secret_key phải có ít nhất 32 ký tự (HMAC SHA256
    // trong Keys.hmacShaKeyFor -> tối thiểu 256bit <=> 32 bytes <=> 32 ký tự)
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConst.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String jwt = Jwts.builder()
                .setIssuer("NguyenTien")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .claim("roles", roles)
                .signWith(key)
                .compact();
        return jwt;
    }

    public static Claims getClaimsFromJwtToken(String jwt) {
        // Bearer token
        jwt = jwt.substring(7);
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
    }

    public static List<String> getRolesFromJwtToken(String jwt) {
        Claims claims = getClaimsFromJwtToken(jwt);
        return (List<String>) claims.get("roles");
    }

    public static String getEmailFromJwtToken(String jwt) {

        // Bearer token
        jwt = jwt.substring(7);

        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));

        return email;
    }
}
