package com.base.thread;

import com.base.model.BaseRet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Created by mylover on 31/10/2016.
 */
public class CallableService implements Callable {

    protected static final Logger logger = LoggerFactory.getLogger(CallableService.class);



    @Override
    public Object call() throws Exception {

        logger.info("子线程执行");

        BaseRet<String> ret = new BaseRet<>();

        ret.setResult("子线程一执行成功");

        ret.setRetCode("000000");
        ret.setRetMsg("success");

        return ret;
    }

}
