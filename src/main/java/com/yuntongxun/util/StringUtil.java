package com.yuntongxun.util;

import com.base.common.EncryptUtil;

import java.util.UUID;

/**
 * <p>Title: StringUtil</p>
 * <p>Description: 字符处理工具类</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: hisunsray</p>
 * <p>Date: 2012-08-20</p>
 * @author tanglujun
 * @version 1.0
 */
public class StringUtil {

	/**
     * 生成32位随机字符串
     * @return
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    public static String getUUID4MD5() {
        return EncryptUtil.md5(UUID.randomUUID().toString());
    }
}
