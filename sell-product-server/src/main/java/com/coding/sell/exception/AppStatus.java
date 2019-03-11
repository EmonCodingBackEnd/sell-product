/*
 * 文件名称：AppStatus.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 00:33
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100033 新建文件
 ********************************************************************************/
package com.coding.sell.exception;

import lombok.Getter;

@Getter
public enum AppStatus implements com.coding.helpers.tool.cmp.exception.AppStatus {
    DICT_ENUM_NOT_EXIST(3139, "根据字典值找不到对应字典"),
    FROM_JSON_ERRPR(3201, "JSON转换到对象错误"),
    TO_JSON_ERRPR(3202, "对象转换到JSON错误"),
    ;
    private Integer errorCode;

    private String errorMessage;

    AppStatus(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
