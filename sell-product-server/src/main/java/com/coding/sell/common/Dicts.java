/*
 * 文件名称：Dicts.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 19:59
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903091959 新建文件
 ********************************************************************************/
package com.coding.sell.common;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.coding.sell.exception.AppStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义字典.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190309 19:59</font><br>
 * 有两种定义形式：1、接口；2、枚举。如果需要校验数据是否合法，推荐枚举的定义方式。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Dicts {

    Logger log = LoggerFactory.getLogger(Dicts.class);

    interface BaseEnum<T> {
        T getValue();
    }

    /** 根据String值获取枚举实例，如果找不到则返回null. */
    static <T extends BaseEnum> T getByValue(Class<T> enumClazz, String value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        return null;
    }

    /** 根据Integer值获取枚举实例，如果找不到则返回null. */
    static <T extends BaseEnum> T getByValue(Class<T> enumClazz, Integer value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        return null;
    }

    /** 根据String值获取枚举实例，如果找不到则抛异常. */
    static <T extends BaseEnum> T getByValueNoisy(Class<T> enumClazz, String value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        log.error("【字典查询】根据字典值找不到对应字典, enumClazz={}, value={}", enumClazz, value);
        throw new AppException(AppStatus.DICT_ENUM_NOT_EXIST);
    }

    /** 根据Integer值获取枚举实例，如果找不到则抛异常. */
    static <T extends BaseEnum> T getByValueNoisy(Class<T> enumClazz, Integer value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        log.error("【字典查询】根据字典值找不到对应字典, enumClazz={}, value={}", enumClazz, value);
        throw new AppException(AppStatus.DICT_ENUM_NOT_EXIST);
    }

    // ==================================================华丽的分割线==================================================

}
