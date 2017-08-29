package com.tumbleweed.test.base.context;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

/**
 * @Description: Spring上下文配置
 * @see: SpringRootConfig 此处填写需要参考的类
 * @version 2014年8月6日 上午11:19:14
 * @author John
 */
@Configuration
@EnableAspectJAutoProxy
@EnableScheduling
@Import({ DubboConfig.class, DubboClientConfig.class})
@ComponentScan(
    basePackages = "com.tumbleweel.hui10.base.context",
    excludeFilters = {@Filter(Controller.class), @Filter(Configuration.class)}
)
public class SpringRootConfig {

    @Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
