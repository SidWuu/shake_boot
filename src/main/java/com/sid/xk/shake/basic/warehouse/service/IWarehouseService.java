package com.sid.xk.shake.basic.warehouse.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sid.xk.shake.basic.warehouse.entity.BasicWarehouse;
import com.sid.xk.shake.basic.warehouse.vo.WarehouseBean;
import com.sid.xk.shake.basic.warehouse.vo.WarehouseQuery;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
public interface IWarehouseService extends IService<BasicWarehouse> {

    /**
     * 分页查询
     * @param form 查询条件
     * @return Page<Company>
     */
    Page<BasicWarehouse> queryPage(WarehouseQuery form);

    /**
     * 查询详情
     * @param warehouseCode 仓库代码
     * @return
     */
    WarehouseBean getBean(String warehouseCode);

    /**
     * 新增
     * @param bean 保存bean
     */
    void insert(WarehouseBean bean);

    /**
     * 修改
     * @param bean 保存bean
     */
    void update(WarehouseBean bean);

    /**
     * 删除
     * @param warehouseCode 仓库代码
     */
    void delete(String warehouseCode);

}
