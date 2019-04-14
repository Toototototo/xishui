package com.tooto.xishui.config;

import com.alibaba.fastjson.JSON;
import com.tooto.xishui.config.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tooto
 * @className CheckInputParameterAspect
 * @Description 统一参数拦截处理
 * @since 2019/4/14 15:55
 */
public class CheckInputParameterAspect {
    private static final Log Logger = LogFactory.getLog(CheckInputParameterAspect.class);

    public static final String UNVALIDATED_INPUT = "输入参数含有非法字符";

    /**
     * 定义切入点:拦截controller层所有方法
     */
    //@Pointcut("execution(* com.ecmp.springboot.contract.controller..*(..))")
    public void params() {
    }

    /**
     * 定义环绕通知
     *
     * @param joinPoint 切点
     * @throws Throwable 抛出异常
     */
    //@Around("params()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            List<Object> argList = new ArrayList<>();
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Serializable && !(args[i] instanceof BindingResult)) {
                        argList.add(args[i]);
                    }
                }
            }
            Logger.info("参数：" + JSON.toJSONString(argList));
            for (Object object : argList) {
                checkParameter(object);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        Object result = joinPoint.proceed();
        return result;
    }

    /**
     * 检查参数
     *
     * @param obj
     * @throws Exception
     */
    public void checkParameter(Object obj) throws Exception {
        // 得到类对象
        Class objClass = obj.getClass();
        // 得到类中的所有属性集合
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            // 如果类型是String
            if ("class java.lang.String".equals(field.getGenericType().toString())) {
                // 拿到该属性的get方法
                Method m = obj.getClass().getMethod("get" + getMethodName(field.getName()));
                // 调用getter方法获取属性值
                String val = (String) m.invoke(obj);
                if (val != null) {
                    if (IllegalStrFilterUtil.isSpecialChar(val)) {
                        throw new BusinessException(UNVALIDATED_INPUT);
                    }
                }
            }
        }
    }

    /**
     * 把一个字符串的第一个字母大写
     *
     * @param fildeName
     * @return string
     * @throws Exception
     */
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
