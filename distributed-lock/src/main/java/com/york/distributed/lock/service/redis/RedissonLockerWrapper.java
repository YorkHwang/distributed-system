package com.york.distributed.lock.service.redis;


import com.york.distributed.lock.service.AcquireLockException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 基于Redisson的并发锁
 * @Author: York.Hwang
 * @Time: 2020/4/16 14:20
 */
@Component
public class RedissonLockerWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonLockerWrapper.class);
    private final static String LOCKER_PREFIX = "redisson:lock:";

    /**
     * 获取锁时长，默认时长60s
     */
    private final static long LOCK_SECS = 60L;

    /**
     * 获取锁时等待时间
     */
    private final static long LOCK_WAIT_SECS = 1L;

    @Autowired
    private RedissonLocker redissonLocker;


    public <T> T lock(String resourceName, AcquiredLockWorker<T> worker) throws AcquireLockException {
        return lock(resourceName, LOCK_SECS, worker);
    }


    public <T> T lock(String resourceName, long lockTime, AcquiredLockWorker<T> worker) throws AcquireLockException {

        final RedissonClient redissonClient = redissonLocker.redissonClient();
        final String redisResourceName = LOCKER_PREFIX + resourceName;
        LOGGER.info("lock redisResourceName: {}", redisResourceName);
        final RLock lock = redissonClient.getLock(redisResourceName);

        boolean success;
        try {
            success = lock.tryLock(LOCK_WAIT_SECS, lockTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            throw new AcquireLockException("获取锁中断");
        }

        if (success) {
            try {
                return worker.workAfterLockAcquired();
            } finally {
                lock.unlock();
            }
        }

        LOGGER.warn("failed to acquire lock, resourceName:{}", redisResourceName);
        throw new AcquireLockException("有并行操作进行中，请稍后重试");
    }

}
