package com.sid.xk.shake.component;

import com.sid.xk.shake.utils.StringUtil;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 加载类
 *
 * @author wuxiaodong
 * @date 2021/04/04
 */
@Component
public class RedisComp {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key  键
     * @param time 时间(秒)
     * @return boolean
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据 key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断 key 是否存在
     * @param key 键
     * @return Boolean
     */
    public boolean hasKey(String key) {
        try {
            Boolean has = redisTemplate.hasKey(key);
            if (null == has) {
                return false;
            }
            return has;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 删除指定前缀的一系列key
     * @param prefix key 前缀
     */
    public void removeAll(String prefix) {
        try {
            Set<String> keys = keys(prefix);
            if (null != keys) {
                redisTemplate.delete(keys);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private Set<String> keys(String prefix) {
        String realKey = prefix + "*";
        try {
            return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
                Set<String> binaryKeys = new HashSet<>();
                Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match(realKey).count(Integer.MAX_VALUE).build());
                while (cursor.hasNext()) {
                    binaryKeys.add(new String(cursor.next()));
                }

                return binaryKeys;
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================================================== String ================================================== */

    /**
     * 获取 String 缓存
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 放入 String 缓存
     * @param key   键
     * @param value 值
     * @return boolean
     */
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

    /**
     * 放入 String 缓存并设置过期时间
     * @param key   键
     * @param value 值
     * @param time  过期时间(秒), 小于等于0则无期限
     * @return boolean
     */
    public boolean set(String key, Object value, long time) {
        boolean result = false;
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 原子类获取最大单据号
     * @param key 键
     * @return long
     */
    public long getMaxCode(String key) {
        RedisAtomicLong atomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        return atomicLong.getAndIncrement();
    }

    /* ================================================== Hash ================================================== */

    /**
     * 获取 Hash 项值
     * @param key   键
     * @param field 项
     * @return 值
     */
    public Object hGet(String key, String field) {
        if (StringUtil.isOneEmpty(key, field)) {
            return null;
        }
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取 Hash 缓存
     * @param key 键
     * @return Map
     */
    public Map<Object, Object> hmGet(String key) {
        return null == key ? null : redisTemplate.opsForHash().entries(key);
    }

    /**
     * 放入 Hash 缓存
     * @param key 键
     * @param map Map 对象
     * @return boolean
     */
    public boolean hmSet(String key, Map<String, Object> map) {
        return hmSet(key, map, 0);
    }

    /**
     * 放入 Hash 缓存并设置时间
     * @param key  键
     * @param map  Map 对象
     * @param time 过期时间(秒)
     * @return true成功 false失败
     */
    public boolean hmSet(String key, Map<String, Object> map, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                result = expire(key, time);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向 Hash 表中放入数据, 如果不存在将创建
     * @param key   键
     * @param field 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hSet(String key, String field, Object value) {
        return hSet(key, field, value, 0);
    }

    /**
     * 向 Hash 表中放入数据并设置时间, 如果不存在将创建
     * @param key   键
     * @param field 项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的 Hash 表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hSet(String key, String field, Object value, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().put(key, field, value);
            if (time > 0) {
                result = expire(key, time);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除 Hash 表中的值
     * @param key   键 不能为null
     * @param field 项 可以使多个 不能为null
     * @return 删除个数
     */
    public long hDel(String key, Object... field) {
        return redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 判断 Hash 表中是否有该项的值
     * @param key   键 不能为null
     * @param field 项 不能为null
     * @return boolean
     */
    public boolean hHasKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * Hash 递增 如果不存在将创建, 并返回新增后的值
     * @param key   键
     * @param field 项
     * @param by    要增加几(大于0)
     * @return double 递增后值
     */
    public double hIncr(String key, String field, double by) {
        return redisTemplate.opsForHash().increment(key, field, by);
    }

    /**
     * Hash 递减
     * @param key   键
     * @param field 项
     * @param by    要减少记(小于0)
     * @return 递减后值
     */
    public double hDecr(String key, String field, double by) {
        return redisTemplate.opsForHash().increment(key, field, -by);
    }

    /* ================================================== Set ================================================== */

    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return Set<Object>
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询 value 在 Set 中是否存在
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public boolean sHasKey(String key, Object value) {
        boolean result = false;
        try {
            result = redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 放入 Set 缓存
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        return sSet(key, 0, values);
    }

    /**
     * 放入 Set 缓存并设置过期时间
     * @param key    键
     * @param time   过期时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取 Set 缓存的长度
     * @param key 键
     * @return 长度
     */
    public long sSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 从 Set 中移除 value
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* ================================================== List ================================================== */

    /**
     * 获取 List 缓存的内容
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取 List 缓存的长度
     * @param key 键
     * @return long
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key   键
     * @param index 索引 index>=0时, 0 表头, 1 第二个元素, 依次类推; index<0时, -1, 表尾, -2倒数第二个元素, 依次类推
     * @return Object
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 放入 List 缓存
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public boolean lSet(String key, List<Object> value) {
        return lSet(key, value, 0);
    }

    /**
     * 放入 List 缓存并设置过期时间
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return boolean
     */
    public boolean lSet(String key, List<Object> value, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                result = expire(key, time);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向 List 中放入数据, 如果不存在将创建
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public boolean lSet(String key, Object value) {
        return lSet(key, value, 0);
    }

    /**
     * 向 List 中放入数据并设置过期时间, 如果不存在将创建
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return boolean
     */
    public boolean lSet(String key, Object value, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                result = expire(key, time);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return boolean
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForList().set(key, index, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 移除所有值为value
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
