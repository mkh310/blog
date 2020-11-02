package cn.mkh.blog.aspect;

import cn.mkh.blog.web.domain.SysLog;
import cn.mkh.blog.web.service.SysLogService;
import cn.mkh.blog.web.controller.admin.SysLogController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;


    @Around("execution(* cn.mkh.blog.web.controller..*(..))")
    public Object logAround(ProceedingJoinPoint jp) throws Throwable {
        //获取方法执行时间
        Date startDate = new Date();
        //获取执行类
        Class executionClass = jp.getTarget().getClass();

        //获取执行方法名称
        String executionMethodName = jp.getSignature().getName();
        //获取执行方法
        Object[] args = jp.getArgs();
        Method executionMethod = null;
        if (args == null || args.length == 0) {
            executionMethod = executionClass.getMethod(executionMethodName);//获取无参方法
        } else {
            Class[] classesArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classesArgs[i] = args[i].getClass();
            }
            executionMethod = getMethod(executionClass, classesArgs);
        }

        SysLog sysLog = new SysLog();

        Object proceed = null;
        try {
            proceed = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            String message = throwable.toString();
            sysLog.setExceptionMessage(message);
        } finally {
            if (executionClass != SysLogController.class) {
                //获取类上的注解
                RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);

                if (classAnnotation != null) {

                    sysLog.setVisitTime(startDate);
                    //获取方法上的注解
                    GetMapping getMapping = executionMethod.getAnnotation(GetMapping.class);
                    String url = null;
                    if (getMapping != null) {
                        String[] value = getMapping.value();
                        url = value.length == 0 ? classAnnotation.value()[0] : classAnnotation.value()[0] + getMapping.value()[0];
                    } else {
                        PostMapping postMapping = executionMethod.getAnnotation(PostMapping.class);
                        url = classAnnotation.value()[0] + postMapping.value()[0];
                    }
                    sysLog.setUrl(url);
                    //获取执行时长
                    Long excutionTime = new Date().getTime() - startDate.getTime();
                    sysLog.setExecutionTime(excutionTime);
                    //获取IP
                    String remoteAddr = request.getRemoteAddr();
                    sysLog.setIp(remoteAddr);

                    //设置类名
                    sysLog.setMethod("[类名]" + executionClass.getSimpleName() + "[方法名]" + executionMethod.getName());

                    sysLogService.save(sysLog);
                }
            }
        }
        return proceed;
    }

    //获取类的方法
    private Method getMethod(Class executionClass, Class[] classesArgs) {
        Method[] methods = executionClass.getMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length == classesArgs.length) {
                if ((checkArgsType(method.getParameterTypes(), classesArgs))) {
                    return method;
                }
            } else {
                continue;
            }
        }

        return null;
    }

    //判断类和参数是否相同
    private boolean checkArgsType(Class<?>[] sourceArgsClass, Class<?>[] argsClass) {

        for (int i = 0; i < sourceArgsClass.length; i++) {
            //判断sourceARGScLAss是否b的父类或者父接口
            if (!sourceArgsClass[i].isAssignableFrom(argsClass[i])){
                //判断是否是基本类型
                if(argsClass[i].getTypeName().equals(sourceArgsClass.toString())){
                    continue;
                }
            }
        }
        return true;
    }
}
