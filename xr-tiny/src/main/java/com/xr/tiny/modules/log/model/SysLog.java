package com.xr.tiny.modules.log.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuan
 * @since 2022-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="系统操作日志")
public class SysLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
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
