package com.wula.stumanage.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import com.wula.stumanage.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */





@Slf4j
public class JwtUtil {
    /**
     * 过期5分钟
     * */
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * jwt密钥
     * */
    private static final String SECRET = "jwt_secret_zcnp";

    /**
     * 生成jwt字符串，五分钟后过期  JWT(json web token)
     * @param user
     * @param
     * @return
     * */
    public static String sign(User user) {
        log.info("sign begin. 获取token～");
        try {
            //获取当前时间加设置使用时间  date即为过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // headers
            Map<String, Object> herders = new HashMap<>(2);
            herders.put("typ", "JWT");
            herders.put("alg", "HMAC256");
            return JWT.create()
                    .withAudience(user.getUserName())
                    //head头信息
                    .withHeader(herders)
                    // 第二部分：paylod  载客
                    .withClaim("userName", user.getUserName())
                    .withClaim("role", user.getIdentity())
                    //主题
                    .withSubject("StuManage_test")
                    //过期时间
                    .withExpiresAt(date)
                    ////第三部分：signature  SignatureAlgorithm.HS256加密算法
                    .sign(algorithm);

        } catch (Exception e) {
            e.printStackTrace();
            log.info("sign error.获取失败");
            return e.getMessage();
        }
    }


    /**
     * 校验token
     * @param token
     * @return
     * */
    public static boolean checkSign(String token) {
        log.info("verify begin.校验token");
        try {
            Algorithm algorithm  = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            log.info("verify end.校验结束");
            return true;
        }catch (JWTVerificationException e) {
            log.info("verify error.token过期");
            return false;
        }
    }

    /**
     * 根据token获取userId
     * @param token
     * @return
     * */
    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根据token获取自定义数据info
     * @param token
     * @return
     * */
    public static Map<String, Object> getInfo(String token) {
        try {
            return JWT.decode(token).getClaim("info").asMap();
        }catch (JWTDecodeException e) {
            return null;
        }
    }

}




