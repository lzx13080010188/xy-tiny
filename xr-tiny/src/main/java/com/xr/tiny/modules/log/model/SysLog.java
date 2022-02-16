package com.xr.tiny.modules.log.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @author macro
 * @since 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="")
public class SysLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("REGISTER_NAME")
    private String registerName;

    @TableField("USER_ID")
    private Integer userId;

    @TableField("USER_NAME")
    private String userName;

    @TableField("PERATION")
    private String peration;

    @TableField("METHOD")
    private String method;

    @TableField("PARAMS")
    private String params;

    @TableField("IP")
    private String ip;

    @TableField("CREATE_DATE")
    private Date createDate;

    @TableField("OPERATE_RESULT")
    private String operateResult;

    @TableField("ABNORMITY")
    private String abnormity;


}
