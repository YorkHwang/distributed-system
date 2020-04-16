package com.york.distributed.lock.controller;

import com.york.distributed.common.util.ServiceResult;
import com.york.distributed.lock.service.AcquireLockException;
import com.york.distributed.lock.service.redis.RedisSetNxLocker;
import com.york.distributed.lock.service.redis.RedissonLockerWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
@RequestMapping("/lock")
@Controller
@Slf4j
public class LockerController {

    private long sleepMills = 2000;

    @Autowired
    RedisSetNxLocker setNxLocker;

    @Autowired
    RedissonLockerWrapper redissonLockerWrapper;

    /**
     * @Description: redis setnx方式实现
     * @Author: York.Hwang
     * @Time: 2020/4/16 11:17
     */
    @RequestMapping("/redis/nx")
    @ResponseBody
    public ServiceResult<String> redisNxLocker() {
        try {
            String key = "redisNxLocker";
            setNxLocker.lock(key);
            Thread.sleep(sleepMills);
            setNxLocker.unLock(key);
        } catch (Exception e) {
            log.error("", e);
            if (e instanceof AcquireLockException) {
                return ServiceResult.serverError(e.getMessage());
            }
            return ServiceResult.serverError("获取锁异常");
        }

        return ServiceResult.success("获取锁成功！");
    }


    /**
     * @Description: redisson 方式实现
     * @Author: York.Hwang
     * @Time: 2020/4/16 11:17
     */
    @RequestMapping("/redis/redisson")
    @ResponseBody
    public ServiceResult<String> redissonLocker() {
        String result;
        try {
            String resource = "redissonLocker";
            result = redissonLockerWrapper.lock(resource, () -> {
                try {
                    Thread.sleep(sleepMills);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                return "获取锁并执行成功！";
            });

        } catch (final Exception e) {
            log.error("", e);
            if (e instanceof AcquireLockException) {
                return ServiceResult.serverError(e.getMessage());
            }
            return ServiceResult.serverError("获取锁异常");
        }

        return ServiceResult.success(result);
    }


}
