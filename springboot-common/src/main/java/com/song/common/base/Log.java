package com.song.common.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author song 定义Log注解用于记录操作日志
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * log 说明
     *
     * @return
     */
    String value() default "";

    /**
     * 是否忽略,比如类上面加的有注解,类中某一个方法不想打印可以设置该属性为 true
     *
     * @return
     */
    boolean ignore() default false;
}