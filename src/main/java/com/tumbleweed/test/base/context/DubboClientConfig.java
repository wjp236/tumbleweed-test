/**
 * 
 */
package com.tumbleweed.test.base.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * dubbo客户端配置
 * @author wupeng<wupengg@enn.com>
 *
 */
@Configuration
@ImportResource("classpath:dubbo-client.xml")
public class DubboClientConfig {

}
