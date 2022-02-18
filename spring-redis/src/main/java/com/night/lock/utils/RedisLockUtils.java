package com.night.lock.utils;

import com.night.cache.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class RedisLockUtils {
    private StringRedisTemplate stringRedisTemplate = RedisUtils.getStringRedisTemplate();

    /**
     * 锁前缀
     */
    private static final String ROOT_KEY = "LOCK";

    /**
     * 过期时间 ms
     */
    private static final long EXPIRE = 15000L;
    /**
     * 最长等待时间，ms
     */
    private static final long WAIT_MILLIS = 10000L;

    /**
     * 重试等待时间，ms
     */
    private static final long SLEEP_MILLIS = 500L;

    /**
     * 最多重试次数
     */
    private static final int RETRIES = Integer.MAX_VALUE;

    /**
     * 原子操作释放锁 Lua 脚本
     */
    private static final String LUA_UNLOCK_SCRIPT = "if redis.call(\"get\", KEYS[1]) == ARGV[1] " +
            "then " +
            "return redis.call(\"del\", KEYS[1]) " +
            "else " +
            "return 0 " +
            "end";

    /**
     * 原子操作加锁 Lua脚本
     */
    private static final String LUA_LOCK_SCRIPT = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";


    /**
     * 使用 ThreadLocal 存储 key 的 value 值，防止同步问题
     */
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public boolean lock(String key) {
        return setLock(key, EXPIRE, WAIT_MILLIS, SLEEP_MILLIS, RETRIES);
    }

    /**
     * 获取 Redis 锁
     *
     * @param key         锁名称
     * @param expire      锁过期时间
     * @param retries     最多重试次数
     * @param sleepMillis 重试等待时间
     * @param waitMillis  最长等待时间
     * @return
     */
    private boolean setLock(String key, long expire, long waitMillis, long sleepMillis, int retries) {
        //检查 key 是否为空
        if (key == null || "".equals(key)) {
            return false;
        }

        try {
            long startTime = System.currentTimeMillis();
            key = ROOT_KEY + key;

            //可重入锁判断
            String v = threadLocal.get();
            if (v != null && isReentrantLock(key, v)) {
                return true;
            }

            //获取锁
            String value = UUID.randomUUID().toString();
            while (!this.setNX(key, value, expire)) {
                //超过最大重试次数后获取锁失败
                if (retries-- < 1) {
                    log.error("超过最大重试次数后获取锁失败----------");
                    return false;
                }

//                //等待下一次尝试
//                Thread.sleep(sleepMillis);

                //超过最长等待时间后获取锁失败
                if ((System.currentTimeMillis() - startTime) > waitMillis) {
                    log.error("超过最长等待时间后获取锁失败----------");

                    return false;
                }
            }

            threadLocal.set(value);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * SET if Not eXists
     */
    private boolean setNX(String key, String value, long expire) {

        Long result = stringRedisTemplate.execute(new DefaultRedisScript<>(LUA_LOCK_SCRIPT, Long.class),
                Collections.singletonList(key),
                value, String.valueOf(expire));

        //判断是否成功
        return result != null && Long.parseLong(result.toString()) > 0;

    }

    /**
     * 可重入锁判断
     */
    private boolean isReentrantLock(String key, String v) {
        ValueOperations kvValueOperations = stringRedisTemplate.opsForValue();
        String value = (String) kvValueOperations.get(key);
        if (value == null) {
            return false;
        }

        return v.equals(value);
    }

    /**
     * 释放锁
     */
    public boolean release(String key) {
        if (key == null || "".equals(key)) {
            return false;
        }

        List<String> keys = new ArrayList<>(1);
        keys.add(ROOT_KEY + key);
        String threadLocalVal = threadLocal.get();
        threadLocal.remove();

        try {
            return deleteKey(keys, threadLocalVal);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * 删除 redis key
     * <p>集群模式和单机模式执行脚本方法一样，但没有共同的接口
     * <p>使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而 redis 锁自动过期失效的时候误删其他线程的锁
     */
    private boolean deleteKey(List<String> keys, String args) {
        Long result = stringRedisTemplate.execute(new DefaultRedisScript<>(LUA_UNLOCK_SCRIPT, Long.class),
                keys,
                args);

        return result != null && Long.parseLong(result.toString()) > 0;
    }
}
