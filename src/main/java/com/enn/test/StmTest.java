package com.enn.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.junit.Test;

/**
 * Created by mylover on 3/23/16.
 */
public class StmTest {

    @Test
    public void stmTest() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("yyy");
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("localhost:8080");
        registry.setUsername("aaa");
        registry.setPassword("bbb");
    }

}
