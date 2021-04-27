package com.sid.xk.shake.basic.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.basic.warehouse.entity.BasicWarearea;
import com.sid.xk.shake.basic.warehouse.entity.BasicWarehouse;
import com.sid.xk.shake.basic.warehouse.mapper.WarehouseMapper;
import com.sid.xk.shake.basic.warehouse.service.IWareareaService;
import com.sid.xk.shake.basic.warehouse.service.IWarehouseService;
import com.sid.xk.shake.basic.warehouse.vo.WarehouseBean;
import com.sid.xk.shake.basic.warehouse.vo.WarehouseQuery;
import com.sid.xk.shake.component.RedisComp;
import com.sid.xk.shake.constants.BaseConstants;
import com.sid.xk.shake.constants.BillEnum;
import com.sid.xk.shake.exception.BaseException;
import com.sid.xk.shake.system.rule.service.IBillCodeService;
import com.sid.xk.shake.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, BasicWarehouse> implements IWarehouseService {

    @Autowired
    private IWareareaService wareareaService;
    @Autowired
    private IBillCodeService billCodeService;
    @Autowired
    private RedisComp redisComp;

    @Override
    public Page<BasicWarehouse> queryPage(WarehouseQuery form) {
        BasicWarehouse warehouse = new BasicWarehouse();
        BeanUtils.copyProperties(form, warehouse);
        warehouse.setWarehouseStatus(BaseConstants.STATUS_0);
        warehouse.setIsDel(BaseConstants.STATUS_0);
        QueryWrapper<BasicWarehouse> query = new QueryWrapper<BasicWarehouse>().setEntity(warehouse);
        Page<BasicWarehouse> page = new Page<>(form.getCurrent(), form.getSize());
        return page(page, query);
    }

    @Override
    public List<BasicWarearea> queryDetail(String warehouseCode) {
        Objects.requireNonNull(warehouseCode, "参数为空");
        return wareareaService.lambdaQuery().eq(BasicWarearea::getWarehouseCode, warehouseCode).list();
    }

    @Override
    public WarehouseBean getBean(String warehouseCode) {
        Objects.requireNonNull(warehouseCode, "参数为空");
        BasicWarehouse main = lambdaQuery().eq(BasicWarehouse::getWarehouseCode, warehouseCode).one();
        Objects.requireNonNull(main, "仓库信息不存在");
        List<BasicWarearea> details = queryDetail(warehouseCode);
        WarehouseBean bean = new WarehouseBean();
        bean.setMain(main);
        bean.setDetails(details);
        return bean;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void insert(WarehouseBean bean) {
        Objects.requireNonNull(bean, "参数为空");
        Objects.requireNonNull(bean.getMain(), "参数为空");
        BasicWarehouse main = bean.getMain();
        // 默认值
        setDefault(main);
        // 校验仓库
        String msg = checkMain(main);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        // 校验重复
        BasicWarehouse check = lambdaQuery().eq(BasicWarehouse::getWarehouseName, main.getWarehouseName()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s]仓库已存在", main.getWarehouseName()));
        }
        // 保存仓库
        boolean success = true;
        try {
            success = save(main);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("保存失败");
        }
        // 保存库位
        saveDetail(bean, BaseConstants.DATA_FLAG_0);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(WarehouseBean bean) {
        Objects.requireNonNull(bean, "参数为空");
        Objects.requireNonNull(bean.getMain(), "参数为空");
        BasicWarehouse main = bean.getMain();
        // 校验仓库
        String msg = checkMain(main);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        BasicWarehouse old = lambdaQuery().eq(BasicWarehouse::getWarehouseCode, main.getWarehouseCode()).one();
        Objects.requireNonNull(old, "仓库信息不存在");
        // 校验重复
        BasicWarehouse check = lambdaQuery().eq(BasicWarehouse::getWarehouseName, main.getWarehouseName()).ne(BasicWarehouse::getWarehouseCode, main.getWarehouseCode()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s]仓库已存在", main.getWarehouseName()));
        }
        // 更新仓库
        boolean success = true;
        try {
            main.setId(old.getId());
            main.setWarehouseCode(old.getWarehouseCode());
            main.setLastUpdateTime(new Date());
            success = updateById(main);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("更新失败");
        }
        // 更新库位(可能新增, 修改, 删除)
        saveDetail(bean, BaseConstants.DATA_FLAG_1);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete(String warehouseCode) {
        Objects.requireNonNull(warehouseCode, "参数为空");
        // 删除仓库
        boolean success = true;
        BasicWarehouse old = lambdaQuery().eq(BasicWarehouse::getWarehouseCode, warehouseCode).one();
        Objects.requireNonNull(old, "仓库信息不存在");
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
        // 删除库位
        try {
            success = wareareaService.remove(new LambdaQueryWrapper<BasicWarearea>().eq(BasicWarearea::getWarehouseCode, warehouseCode));
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("删除库位失败");
        }
    }

    @Override
    public void loadCache() {
        List<BasicWarehouse> list = lambdaQuery().eq(BasicWarehouse::getWarehouseStatus, BaseConstants.STATUS_0).eq(BasicWarehouse::getIsDel, BaseConstants.STATUS_0).list();
        for (BasicWarehouse mod : list) {
            redisComp.set(BillEnum.BasicWarehouse.getRedisKey() + mod.getWarehouseCode(), mod);
        }
    }

    /**
     * 保存明细
     * @param bean 保存信息
     * @param dataFlag 操作标志
     */
    private void saveDetail(WarehouseBean bean, Integer dataFlag) {
        Objects.requireNonNull(bean, "参数为空");
        Objects.requireNonNull(dataFlag, "参数为空");
        List<BasicWarearea> details = bean.getDetails();
        if (null != details && !details.isEmpty()) {
            List<BasicWarearea> inserts = null;
            List<BasicWarearea> updates = null;
            List<Long> deleteIds = null;
            for (BasicWarearea detail : details) {
                if (BaseConstants.DATA_FLAG_0.equals(detail.getDataFlag())) {
                    // 新增
                    detail.setWarehouseCode(bean.getMain().getWarehouseCode());
                    detail.setWareareaCode(billCodeService.getMaxCode(BillEnum.BasicWarearea));
                    String msg = checkDetail(detail);
                    if (StringUtil.isNotEmpty(msg)) {
                        BaseException.throwException(msg);
                    }
                    BasicWarearea check = wareareaService.lambdaQuery().eq(BasicWarearea::getWareareaName, detail.getWareareaName()).eq(BasicWarearea::getWarehouseCode, detail.getWarehouseCode()).one();
                    if (null != check) {
                        BaseException.throwException(String.format("[%s/%s]库位已存在", detail.getWareareaName(), detail.getWarehouseCode()));
                    }
                    if (null == inserts) {
                        inserts = new ArrayList<>();
                    }
                    inserts.add(detail);
                } else if (BaseConstants.DATA_FLAG_1.equals(detail.getDataFlag())) {
                    // 修改
                    String msg = checkDetail(detail);
                    if (StringUtil.isNotEmpty(msg)) {
                        BaseException.throwException(msg);
                    }
                    BasicWarearea old = wareareaService.lambdaQuery().eq(BasicWarearea::getWareareaCode, detail.getWareareaCode()).one();
                    Objects.requireNonNull(old, "库位已不存在");
                    BasicWarearea check = wareareaService.lambdaQuery().eq(BasicWarearea::getWareareaName, detail.getWareareaName()).eq(BasicWarearea::getWarehouseCode, detail.getWarehouseCode()).ne(BasicWarearea::getWareareaCode, detail.getWareareaCode()).one();
                    if (null != check) {
                        BaseException.throwException(String.format("[%s/%s]库位人已存在", detail.getWareareaName(), detail.getWarehouseCode()));
                    }
                    detail.setId(old.getId());
                    detail.setWarehouseCode(old.getWarehouseCode());
                    detail.setWareareaCode(old.getWareareaCode());
                    if (null == updates) {
                        updates = new ArrayList<>();
                    }
                    updates.add(detail);
                } else if (BaseConstants.DATA_FLAG_2.equals(detail.getDataFlag())) {
                    // 删除
                    BasicWarearea old = wareareaService.lambdaQuery().eq(BasicWarearea::getWareareaCode, detail.getWareareaCode()).one();
                    Objects.requireNonNull(old, "库位已不存在");
                    if (null == deleteIds) {
                        deleteIds = new ArrayList<>();
                    }
                    deleteIds.add(detail.getId());
                } else {
                    BaseException.throwException(String.format("无法识别操作标志[%s]", dataFlag));
                }
            }
            saveDetails(inserts);
            updateDetails(updates);
            deleteDetails(deleteIds);
        }
    }

    private void saveDetails(List<BasicWarearea> details) {
        if (null == details || details.isEmpty()) {
            return;
        }
        boolean success = true;
        try {
            success = wareareaService.saveBatch(details, details.size());
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("保存库位失败");
        }
    }

    private void updateDetails(List<BasicWarearea> details) {
        if (null == details || details.isEmpty()) {
            return;
        }
        boolean success = true;
        try {
            success = wareareaService.updateBatchById(details, details.size());
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("更新库位失败");
        }
    }

    private void deleteDetails(List<Long> details) {
        if (null == details || details.isEmpty()) {
            return;
        }
        boolean success = true;
        try {
            success = wareareaService.removeByIds(details);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("更新库位失败");
        }
    }

    private void setDefault(BasicWarehouse warehouse) {
        if (StringUtil.isEmpty(warehouse.getWarehouseCode())) {
            warehouse.setWarehouseCode(billCodeService.getMaxCode(BillEnum.BasicWarehouse));
        }
        Date date = new Date();
        warehouse.setCreateTime(date);
        warehouse.setLastUpdateTime(date);
        warehouse.setWarehouseStatus(BaseConstants.STATUS_0);
        warehouse.setIsDel(BaseConstants.STATUS_0);
    }

    private String checkMain(BasicWarehouse warehouse) {
        String msg = "";
        msg += StringUtil.emptyToMsg(warehouse.getWarehouseCode(), "代码为空");
        msg += StringUtil.emptyToMsg(warehouse.getWarehouseName(), "名称为空");

        if (StringUtil.isNotEmpty(msg) && msg.endsWith(BaseConstants.SPLIT_CHARACTER)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

    private String checkDetail(BasicWarearea linkman) {
        String msg = "";
        msg += StringUtil.emptyToMsg(linkman.getWarehouseCode(), "仓库代码为空");
        msg += StringUtil.emptyToMsg(linkman.getWareareaCode(), "库位代码为空");
        msg += StringUtil.emptyToMsg(linkman.getWareareaName(), "名称为空");

        if (StringUtil.isNotEmpty(msg) && msg.endsWith(BaseConstants.SPLIT_CHARACTER)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

}
