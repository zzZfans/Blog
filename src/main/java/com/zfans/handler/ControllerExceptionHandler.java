package com.zfans.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.SubstituteLogger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
  @author Zfans
 * @date 2020/04/27 11:38
 */

/**
 * ControllerException处理
 *
 * @author Zfans
 */
@ControllerAdvice
public class ControllerExceptionHandler {


    LoggerFactory loggerFactory;
    SubstituteLogger substLogger;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 异常处理
     * 标识方法可以做异常处理
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHander(HttpServletRequest request, Exception e) throws Exception {

        logger.error("Request URL : {} , Exception : {}", request.getRequestURL(), e.getMessage());

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();

        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");

        return mv;
    }


//    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
//
//    /**
//     * 异常处理
//     *
//     * @param request
//     * @param e
//     * @return
//     */
//    @ExceptionHandler({Exception.class})
//    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception {
//
//        logger.error("Request URL : {} , Exception : {}", request.getRequestURL(), e);
//
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
//            throw e;
//        }
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("url", request.getRequestURL());
//        mav.addObject("exception", e);
//        mav.setViewName("error/error");
//
//        return mav;
//    }
}
