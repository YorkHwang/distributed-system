package com.york.distributed.lock.service.redis;

import com.york.distributed.lock.service.DistributedLocker;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractRedisLocker implements DistributedLocker {


    @Autowired
    protected RedisTemplate<String, String> redisTemplate;


    @Autowired
    @Qualifier("redissonSingle")
    private RedissonClient redissonClient;


    public RedissonClient redissonClient(){
        return redissonClient;
    }



}
