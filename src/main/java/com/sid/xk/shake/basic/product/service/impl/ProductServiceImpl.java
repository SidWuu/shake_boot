package com.sid.xk.shake.basic.product.service.impl;

import com.sid.xk.shake.basic.product.entity.BasicProduct;
import com.sid.xk.shake.basic.product.mapper.ProductMapper;
import com.sid.xk.shake.basic.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物资代码表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, BasicProduct> implements IProductService {

}
