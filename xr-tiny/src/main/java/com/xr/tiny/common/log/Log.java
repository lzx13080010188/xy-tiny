package com.xr.tiny.common.log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : lzx
 * @version : V1.0
 * @date : 2022/2/14 下午6:11
 * @description : 日志注解
 */
@Target(ElementType.METHOD) // 方法注解
@Retention(RetentionPolicy.RUNTIME) // 运行时可见
public @interface Log {
    String operateType() default  "";// 记录操作功能
}
