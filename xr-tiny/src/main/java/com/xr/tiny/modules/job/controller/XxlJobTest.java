package com.xr.tiny.modules.job.controller;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author : lzx
 * @version : V1.0
 * @date : 2022/2/21 上午9:39
 * @description :
 */
@Component
public class XxlJobTest {
    @XxlJob("xrJobTest")
    public void xrJobTest(){
        System.out.println("xxlJob执行成功！！！");
    }
}
