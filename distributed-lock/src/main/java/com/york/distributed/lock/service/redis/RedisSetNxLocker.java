package com.york.distributed.lock.service.redis;

import com.york.distributed.lock.service.AcquireLockException;
import com.york.distributed.lock.service.Values;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class RedisSetNxLocker extends AbstractRedisLocker {


    @Override
    public void lock(String lockKey) throws AcquireLockException {
        lock(lockKey, TimeUnit.MILLISECONDS, Values.DEFAULT_SECS);
    }

    @Override
    public void lock(String lockKey, long timeoutMills) throws AcquireLockException {
        lock(lockKey, TimeUnit.MILLISECONDS, timeoutMills);
    }

    @Override
    public void lock(String lockKey, TimeUnit unit, long timeout) throws AcquireLockException {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, Values.V, timeout, unit);
        if (success == null || !success) {
            throw new AcquireLockException("获取锁失败，稍后重试~");
        }
    }

    @Override
    public void unLock(String lockKey) {
        redisTemplate.delete(lockKey);
    }

}
