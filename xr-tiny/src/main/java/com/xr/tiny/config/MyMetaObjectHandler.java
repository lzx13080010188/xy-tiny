package com.xr.tiny.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : lzx
 * @version : V1.0
 * @date : 2022/2/18 上午10:20
 * @description : mybtais切面拦截，将insert，update操作更新时间
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //实现mybtais自动插入生成createTime
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class,LocalDateTime::now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
