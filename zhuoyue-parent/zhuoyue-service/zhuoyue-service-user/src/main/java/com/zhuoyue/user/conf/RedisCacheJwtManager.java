package com.zhuoyue.user.conf;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @author Linmo
 * @create 2020/4/24 14:09
 */
public class RedisCacheJwtManager<V> {
    private static final String PRE = "login:";
    private static final String ACCOUNT_PAiR = "pair:";
    private Base64.Encoder encoder = Base64.getEncoder();
    public static Long TTL = 30 * 60L;
    private RedisTemplate<String, V> redisTemplate;

    public RedisCacheJwtManager(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public V get(String key) {
        return redisTemplate.boundValueOps(PRE + key).get();
    }

    public void set(String key, V val) {
        BoundValueOperations<String, V> kvBoundValueOperations = redisTemplate.boundValueOps(getLoginKey(key));
        kvBoundValueOperations.set(val);
        kvBoundValueOperations.expire(TTL, TimeUnit.SECONDS);
    }

    public boolean delJwt(String jwt) {
        return del0(getLoginKey(jwt));
    }

    public boolean delPair(String username, String password) {
        return del0(getLoginKey(encode(username, password)));
    }

    private boolean del0(String key) {
        return redisTemplate.delete(key);
    }

    public boolean contains(String jwt) {
        return contains0(getLoginKey(jwt));
    }

    private boolean contains0(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean containsPair(String username, String password) {
        return contains0(getPairKey(encode(username, password)));
    }

    public String getPairValue(String username, String password) {
        BoundValueOperations<String, ?> stringVBoundValueOperations = redisTemplate.boundValueOps(getPairKey(encode(username, password)));
        return (String) stringVBoundValueOperations.get();
    }

    public void setPairValue(String username, String password, String jwt) {
        String key = getPairKey(encode(username, password));
        BoundValueOperations<String, Object> stringVBoundValueOperations = (BoundValueOperations<String, Object>) redisTemplate.boundValueOps(key);
        stringVBoundValueOperations.set(jwt);
        stringVBoundValueOperations.expire(TTL, TimeUnit.SECONDS);
    }

    private String getPairKey(String encode) {
        return ACCOUNT_PAiR + encode;
    }

    private String getLoginKey(String jwt) {
        return PRE + jwt;
    }

    private String encode(String username, String password) {
        StringBuilder append = new StringBuilder(username).append(":").append(password);
        byte[] encode = encoder.encode(append.toString().getBytes());
        return new String(encode);
    }
}
