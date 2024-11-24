package com.fiap.burguer.core.application.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    public static DecodedJWT decodeToken(String token) {
        return JWT.decode(token);
    }

    public static Integer getIdFromToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("id").asInt();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCpfFromToken(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return decodedJWT.getClaim("cpf").asString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getNameFromToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        return decodedJWT.getClaim("name").asString();
    }

    public static String getEmailFromToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        return decodedJWT.getClaim("email").asString();
    }

    public static Boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            long expirationTime = decodedJWT.getClaim("exp").asLong();

            long currentTime = System.currentTimeMillis() / 1000;

            return expirationTime < currentTime;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isAdminFromToken(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return decodedJWT.getClaim("isAdmin").asBoolean();
        } catch (Exception e) {
            return false;
        }

    }
}
