package com.night.lock.service;

import com.night.cache.utils.RedisUtils;
import com.night.lock.utils.RedisLockUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonReadLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lock 测试的逻辑
 *
 * @author night
 */
@Slf4j
@Service
public class LockService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisLockUtils redisLockUtils;

    private static final String KEY = "123";
    private static int count = 0;


    private AtomicInteger size = new AtomicInteger(0);
    private AtomicInteger size2 = new AtomicInteger(0);

    public void setNx(int num) {
        System.out.println(size.addAndGet(1));

        try {
            if (redisLockUtils.lock("nx::test::" + num)) {
                // 模拟业务逻辑
                log.info("lock" + size2.addAndGet(1));
//                int random = new Random().nextInt(3);
//                try {
//                    Thread.sleep(1000 * random);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                RedisUtils.set(KEY, count++, 0);
            }
        }finally {
            redisLockUtils.release("nx::test::" + num);
        }
    }

    public boolean rLock(int num) {
        RLock lock = redissonClient.getLock("rlock::test::" + num);
        System.out.println(size.addAndGet(1));
        try {
            // 防止重复
            if (lock.tryLock(1000, 1000, TimeUnit.SECONDS)) {
                // 这里模拟业务
                log.info(String.valueOf(size2.addAndGet(1)));
                int random = new Random().nextInt(3);
                Thread.sleep(1000 * random);
                RedisUtils.set(KEY, count++, 0);
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return false;
    }

}
