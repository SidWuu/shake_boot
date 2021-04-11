package com.sid.xk.shake.basic.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.basic.product.entity.BasicProduct;
import com.sid.xk.shake.basic.product.mapper.ProductMapper;
import com.sid.xk.shake.basic.product.service.IProductService;
import com.sid.xk.shake.basic.product.vo.ProductQuery;
import com.sid.xk.shake.common.constants.BaseConstants;
import com.sid.xk.shake.common.exception.BaseException;
import com.sid.xk.shake.common.utils.StringUtil;
import com.sid.xk.shake.system.rule.service.IBillCodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

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

    @Autowired
    private IBillCodeService billCodeService;

    @Override
    public Page<BasicProduct> queryPage(ProductQuery form) {
        BasicProduct data = new BasicProduct();
        BeanUtils.copyProperties(form, data);
        data.setDataStatus(BaseConstants.STATUS_0);
        data.setIsDel(BaseConstants.STATUS_0);
        QueryWrapper<BasicProduct> query = new QueryWrapper<BasicProduct>().setEntity(data);
        Page<BasicProduct> page = new Page<>(form.getCurrent(), form.getSize());
        return page(page, query);
    }

    @Override
    public BasicProduct getBean(String productCode) {
        Objects.requireNonNull(productCode, "参数为空");
        return lambdaQuery().eq(BasicProduct::getProductCode, productCode).one();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void insert(BasicProduct mod) {
        Objects.requireNonNull(mod, "参数为空");
        // 默认值
        setDefault(mod);
        // 校验物资
        String msg = checkMain(mod);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        // 校验重复
        BasicProduct check = lambdaQuery().eq(BasicProduct::getProductParent, mod.getProductParent()).eq(BasicProduct::getProductName, mod.getProductName()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s/%s]已存在", mod.getProductParent(), mod.getProductName()));
        }
        // 保存物资
        boolean success = true;
        try {
            success = save(mod);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("保存失败");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(BasicProduct mod) {
        Objects.requireNonNull(mod, "参数为空");
        // 校验物资
        String msg = checkMain(mod);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        BasicProduct old = lambdaQuery().eq(BasicProduct::getProductCode, mod.getProductCode()).one();
        Objects.requireNonNull(old, "物资信息不存在");
        // 校验重复
        BasicProduct check = lambdaQuery().eq(BasicProduct::getProductParent, mod.getProductParent()).eq(BasicProduct::getProductName, mod.getProductName()).ne(BasicProduct::getProductCode, mod.getProductCode()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s/%s]已存在", mod.getProductParent(), mod.getProductName()));
        }
        // 更新物资
        boolean success = true;
        try {
            mod.setId(old.getId());
            mod.setProductCode(old.getProductCode());
            mod.setLastUpdateTime(new Date());
            success = updateById(mod);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("保存失败");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete(String productCode) {
        Objects.requireNonNull(productCode, "参数为空");
        // 删除企业
        boolean success = true;
        BasicProduct old = lambdaQuery().eq(BasicProduct::getProductCode, productCode).one();
        Objects.requireNonNull(old, "物资信息不存在");
        if (StringUtil.equals(BaseConstants.STATUS_1, old.getIsDel())) {
            BaseException.throwException("已删除");
        }
        try {
            old.setIsDel(BaseConstants.STATUS_1);
            old.setLastUpdateTime(new Date());
            success = updateById(old);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("删除失败");
        }
    }

    private void setDefault(BasicProduct mod) {
        if (StringUtil.isEmpty(mod.getProductCode())) {
            mod.setProductCode(billCodeService.getMaxCode("basic_product", "product_code"));
        }
        Date date = new Date();
        mod.setCreateTime(date);
        mod.setLastUpdateTime(date);
        mod.setDataStatus(BaseConstants.STATUS_0);
        mod.setIsDel(BaseConstants.STATUS_0);
    }

    private String checkMain(BasicProduct mod) {
        String msg = "";
        msg += StringUtil.emptyToMsg(mod.getProductCode(), "代码为空");
        msg += StringUtil.emptyToMsg(mod.getProductParent(), "品名大类为空");
        msg += StringUtil.emptyToMsg(mod.getProductName(), "品名为空");

        if (StringUtil.isNotEmpty(msg) && msg.endsWith(BaseConstants.SPLIT_CHARACTER)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

}
