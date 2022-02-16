package com.xr.tiny.common.log;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author : lzx
 * @version : V1.0
 * @date : 2022/2/14 下午6:10
 * @description :
 */
@Data
public class SysLogVo {
    @TableId
    private Integer id;

    private String registerName;

    private Integer userId;

    private String userName;

    private String peration;

    private String method;

    private String params;

    private String ip;

    private Date createDate;

    private String operateResult;

    private String abnormity;
}
