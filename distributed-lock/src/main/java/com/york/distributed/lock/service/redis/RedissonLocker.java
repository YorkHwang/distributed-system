package com.york.distributed.lock.service.redis;

import com.york.distributed.lock.service.Values;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class RedissonLocker extends AbstractRedisLocker {

    @Override
    public void lock(String lockKey) {
        lock(lockKey, TimeUnit.SECONDS, Values.DEFAULT_SECS);
    }


    @Override
    public void lock(String lockKey, long timeoutMills) {
        lock(lockKey, TimeUnit.MILLISECONDS, timeoutMills);
    }


    @Override
    public void lock(String lockKey, TimeUnit unit, long timeout) {
        RLock lock = redissonClient().getLock(lockKey);
        lock.lock(timeout, unit);
    }


    @Override
    public void unLock(String lockKey) {
        redissonClient().getLock(lockKey).unlock();
    }

}
