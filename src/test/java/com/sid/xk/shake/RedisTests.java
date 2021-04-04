package com.sid.xk.shake;

import com.sid.xk.shake.common.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author wuxiaodong
 * @date 2021/04/04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShakeApplication.class)
public class RedisTests {

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void set() {
        System.out.println(redisUtil.set("redis_key", "redis_value"));
    }

    @Test
    public void get() {
        System.out.println(redisUtil.getString("redis_key"));
    }

    @Test
    public void getMaxCode() {
        System.out.println(redisUtil.getMaxCode("redis_key"));
    }

}
