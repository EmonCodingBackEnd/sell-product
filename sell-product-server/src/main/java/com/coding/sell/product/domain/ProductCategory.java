/*
 * 文件名称：ProductCategory.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 00:51
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100051 新建文件
 ********************************************************************************/
package com.coding.sell.product.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 商品类目表.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190310 08:04</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product_category")
@Data
public class ProductCategory extends BaseEntity {

    private static final long serialVersionUID = 8555916367367748870L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(
        name = "idGenerator",
        strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy"
    )
    private Long id;

    /** 类目名称. */
    @Column(name = "category_name")
    private String categoryName;

    /** 类目编号. */
    @Column(name = "category_type")
    private Integer categoryType;
}