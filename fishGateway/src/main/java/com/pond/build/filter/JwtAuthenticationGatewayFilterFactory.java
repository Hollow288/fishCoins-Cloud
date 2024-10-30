package com.pond.build.filter;

import com.alibaba.fastjson2.JSON;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.CommonResult;
import com.pond.build.utils.HmacUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class JwtAuthenticationGatewayFilterFactory
        extends AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilterFactory.Config> {

    public JwtAuthenticationGatewayFilterFactory() {
        super(Config.class);
    }

    @Value(value = "${jwt.key:}")
    private String secretKey;

    @Value(value = "${gateway.key:}")
    private String gatewayKey;


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 其他路径校验 JWT
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);  // 去掉 "Bearer " 前缀
                try {
                    byte[] encodedKey = Base64.getDecoder().decode(secretKey);
                    Claims claims = Jwts.parser()
                            .verifyWith(new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256"))
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();

                    long timestamp = System.currentTimeMillis();
                    // 将用户信息添加到请求头中
                    exchange.getRequest().mutate()
                            .header("X-User-Roles", claims.getSubject())
                            .header("X-Timestamp", String.valueOf(timestamp))
                            .header("X-Gateway-Signature", HmacUtils.generateHMAC(String.valueOf(timestamp), gatewayKey))
                            .build();
                } catch (Exception e) {
                    // 如果解析失败，则返回 401 未授权
                    return sendCustomResponse(exchange, HttpStatus.OK, HttpStatusCode.FORBIDDEN_ROLE_ERR.getCnMessage());
                }
            } else {
                // 如果没有提供有效的 JWT，则返回 401
                return sendCustomResponse(exchange, HttpStatus.OK, HttpStatusCode.FORBIDDEN_ROLE_ERR.getCnMessage());
            }
            return chain.filter(exchange);
        };
    }

    // 配置类，用于封装过滤器的参数
    public static class Config {

        //Todo 这里的配置一直拿不到 搞不定

//        private String secretKey;
//
//        public String getSecretKey() {
//            return secretKey;
//        }
//
//        public void setSecretKey(String secretKey) {
//            this.secretKey = secretKey;
//        }
    }


    private Mono<Void> sendCustomResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // 使用 FastJSON 创建 JSON 响应体
        String jsonResponse = JSON.toJSONString(new CommonResult<>(HttpStatusCode.FORBIDDEN_ROLE_ERR.getCode(),message));

        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        DataBuffer buffer = bufferFactory.wrap(jsonResponse.getBytes(StandardCharsets.UTF_8));

        return exchange.getResponse().writeWith(Mono.just(buffer));
    }




}
