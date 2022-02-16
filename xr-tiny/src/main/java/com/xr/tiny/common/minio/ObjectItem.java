package com.xr.tiny.common.minio;

import lombok.Data;

/**
 * @author : lzx
 * @version : V1.0
 * @date : 2022/2/13 下午5:18
 * @description :
 */
@Data
public class ObjectItem {
    private Long size;
    private String objectName;
}
