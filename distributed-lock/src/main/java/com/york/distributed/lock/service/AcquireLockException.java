package com.york.distributed.lock.service;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
public class AcquireLockException extends Exception {

    public AcquireLockException(){
        super();
    }

    public AcquireLockException(String msg){
        super(msg);
    }

}
