package com.tumbleweed.test.base.context;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Created by mylover on 12/10/2016.
 */
public class StartupTest {

    public static void main(String[] args) throws Exception {
        // 创建Spring上下文
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringRootConfig.class);

    }

}
