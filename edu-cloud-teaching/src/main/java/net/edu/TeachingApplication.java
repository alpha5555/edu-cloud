package net.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @Author: 马佳浩
 * @Date: 2022/9/8 17:57
 * @Version: 1.0
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class TeachingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeachingApplication.class,args);
    }
}
