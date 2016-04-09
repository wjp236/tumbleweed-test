package com.base.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler implements HandlerExceptionResolver {
    public static Logger logger = LogManager.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object obj, Exception ex) {
        // TODO Auto-generated method stub


        logger.info("错误信息" + ex.getMessage());
        Message e = new Message();
        e.setCode(ex.getMessage());
        e.setMsg("不好意思 您错误le ");

        return new ModelAndView("jsonView", "me", e);
    }

}
