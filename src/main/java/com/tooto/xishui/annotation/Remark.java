package com.tooto.xishui.annotation;

import java.lang.annotation.*;

/**
 * @author Tooto
 * @className Remark
 * @Description 用于注解类或属性的备注数据，这些元数据用于运行时动态内容生成
 * @since 2019/4/14 16:53
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PACKAGE })
public @interface Remark {
    /**
     * @return 简要注解说明
     */
    String value();
    /**
     * @return 排序
     */
    int rank() default 0;
    /**
     * @return 注释说明：用于描述代码内部用法说明，一般不用于业务
     */
    String comments() default "";
}

