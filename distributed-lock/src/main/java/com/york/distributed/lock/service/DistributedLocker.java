package com.york.distributed.lock.service;

import java.util.concurrent.TimeUnit;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DistributedLocker {

    /**
     *  @Description: 加锁
     *  @Author: York.Hwang
     *  @Time: 2020/4/16 01:08
     */
    void lock(String lockKey) throws AcquireLockException;


    /**
     *  @Description: 按毫秒加锁
     *  @Author: York.Hwang
     *  @Time: 2020/4/16 11:26
     */
    void lock(String lockKey, long timeoutMills) throws AcquireLockException;

    /**
     *  @Description: 按时间单位加锁
     *  @Author: York.Hwang
     *  @Time: 2020/4/16 11:26
     */
    void lock(String lockKey, TimeUnit unit , long timeout) throws AcquireLockException;


    /**
     *  @Description:解锁
     *  @Author: York.Hwang
     *  @Time: 2020/4/16 01:08
     */
    void unLock(String lockKey);

}
