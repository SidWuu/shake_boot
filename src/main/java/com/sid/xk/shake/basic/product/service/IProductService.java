package com.sid.xk.shake.basic.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sid.xk.shake.basic.product.entity.BasicProduct;
import com.sid.xk.shake.basic.product.vo.ProductQuery;

/**
 * <p>
 * 物资代码表 服务类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
public interface IProductService extends IService<BasicProduct> {

    /**
     * 分页查询
     * @param form 查询条件
     * @return Page<Company>
     */
    Page<BasicProduct> queryPage(ProductQuery form);

    /**
     * 查询详情
     * @param productCode 物资代码
     * @return
     */
    BasicProduct getBean(String productCode);

    /**
     * 新增
     * @param mod 保存bean
     */
    void insert(BasicProduct mod);

    /**
     * 修改
     * @param mod 保存bean
     */
    void update(BasicProduct mod);

    /**
     * 删除
     * @param productCode 物资代码
     */
    void delete(String productCode);

    /**
     * 加入缓存
     * key: BasicProduct-product_code-{productParent}, value: Map<{productCode}, BasicProduct>
     */
    void loadCache();

}
