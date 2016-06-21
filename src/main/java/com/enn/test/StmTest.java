package com.enn.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.enn.common.SftpClientUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by mylover on 3/23/16.
 */
public class StmTest {

    public Logger log = LogManager.getLogger(StmTest.class);

    /**
     * 校验参数不为null或""
     * （作者：peijiaqi<peijiaqi@ennew.com>）
     * @param params 返回boolean类型且为true 则校验通过  返回key值则为校验有误
     * @return
     */
    public static Object checkParams(Map params) {
        Iterator entries = params.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || "".equals(value)) {
                return key;
            }
        }
        return true;
    }

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

    @Test
    public void testCheckParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("acctNo", "1");
        params.put("payType", "1");
        Object checkRet = StmTest.checkParams(params);
        if (!(checkRet instanceof Boolean)) {
            log.info(checkRet);
        }
    }

    @Test
    public void testSftp() {
        String ftpPath = "/data/home/dev/file/wx";
        String ftpfile = "/Users/mylover/Downloads/file.java";
        SftpClientUtil fa = new SftpClientUtil();
        fa.mkdir(ftpPath);
//        fa.upload("10.37.148.254", 22, "dev", "dev", ftpPath, ftpfile);
    }

}
