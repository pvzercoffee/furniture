package com.pvzer.furniture.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String SECRET_KEY = "my-super-long-secret-key-1234567890";

    private static long EXPIRATION_TIME = 12 * 60 * 60 * 1000;


    public static String generateToken(Map<String,Object> claims)
    {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        return Jwts.builder()
                //HS256的密钥可以是明文（长度必须大于32），ES256必须是密文
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(claims)
                .setExpiration(expirationDate)
                .compact();
    }

    private static Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
