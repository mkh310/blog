package cn.mkh.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


//指明这个拦截器拦截所有的controller类
@ControllerAdvice
public class MyExceptionHandler {

    //获取Logger对象
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request,Exception e) throws Exception {

        //日志输出异常信息以及异常url
        logger.error("Request URL : {},Exception : {}",request.getRequestURL(),e);


        //判断该异常是否有异常状态注解，有的话让springboot进行处理
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
