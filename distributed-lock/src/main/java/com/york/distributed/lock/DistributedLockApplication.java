package com.york.distributed.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @Description: 分布式锁启动入口
 *  @Author: York.Hwang
 *  @Time: 2020/4/15 00:28
 */
@SpringBootApplication
public class DistributedLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedLockApplication.class, args);
		System.out.println("\nServer started successfully!\n");
	}

}
