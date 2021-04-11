package com.sid.xk.shake.common.component;

import com.sid.xk.shake.basic.warehouse.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 初始化
 * @author wuxiaodong
 * @date 2021/04/11
 */
@Component
public class LoadComp {

    @Autowired
    private IWarehouseService warehouseService;

    @PostConstruct
    public void initCache() {
        warehouseService.loadCache();
    }

}
