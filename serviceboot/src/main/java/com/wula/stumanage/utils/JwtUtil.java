package com.wula.stumanage.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Slf4j
public class JwtUtil {
    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "c369a1e4-43ee-4e1e-b130-2b952f1ba9ad";
    /**
     * 签名方法
     * @Param userName  userName
     * @Param role  role
     * @return String role
     */
    public static String sign(String userName, String role) {
        log.info("sign begin.");
        try {
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 秘钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // headers
            Map<String, Object> herders = new HashMap<>(2);
            herders.put("typ", "JWT");
            herders.put("alg", "HS256");
            log.info("sign end.");
            return JWT.create()
                    .withHeader(herders)
                    .withClaim("userName", userName)
                    .withClaim("role", role)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.info("sign error.");
            return exception.getMessage();
        }
    }
    /**
     * token 校验方法
     * @Param userName  userName
     * @return String role
     */
    public static boolean verify(String token) {
        log.info("verify begin.");
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            log.info("verify end.");
            return true;
        } catch (Exception exception) {  //UnsupportedEncodingException exception
            log.info("verify error.");
            exception.printStackTrace();
            return false;
        }
    }
}
