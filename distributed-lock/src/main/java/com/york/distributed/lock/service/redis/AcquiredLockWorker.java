package com.york.distributed.lock.service.redis;

/**
 *  @Description: 获取锁后的执行器
 *  @Author: York.Hwang
 *  @Time: 2020/4/16 14:09
 */
public interface AcquiredLockWorker<T> {

    T workAfterLockAcquired();

}
