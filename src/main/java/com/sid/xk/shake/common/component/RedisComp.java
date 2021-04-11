package com.sid.xk.shake.common.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Redis 加载类
 * @author wuxiaodong
 * @date 2021/04/04
 */
@Component
public class RedisComp {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public String getString(final String key) {
        Object value = get(key);
        return null == value ? null : (String) value;
    }

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public long getMaxCode(String key) {
        RedisAtomicLong atomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        return atomicLong.getAndIncrement();
    }
}
