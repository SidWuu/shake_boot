package com.sid.xk.shake.init.component;

import com.sid.xk.shake.basic.company.service.ICompanyService;
import com.sid.xk.shake.basic.dictionary.service.IDictionaryService;
import com.sid.xk.shake.basic.product.service.IProductService;
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
public class LoadCacheComp {

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IWarehouseService warehouseService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IDictionaryService dictionaryService;

    @PostConstruct
    public void initCache() {
        companyService.loadCache();
        warehouseService.loadCache();
        productService.loadCache();
        dictionaryService.loadCache();
    }

}
