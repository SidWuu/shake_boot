package com.sid.xk.shake.basic.dictionary.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.basic.dictionary.entity.BasicDictionary;
import com.sid.xk.shake.basic.dictionary.mapper.DictionaryMapper;
import com.sid.xk.shake.basic.dictionary.service.IDictionaryService;
import com.sid.xk.shake.basic.dictionary.vo.DictionaryQuery;
import com.sid.xk.shake.component.RedisComp;
import com.sid.xk.shake.constants.BaseConstants;
import com.sid.xk.shake.constants.BillEnum;
import com.sid.xk.shake.exception.BaseException;
import com.sid.xk.shake.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-03-28
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, BasicDictionary> implements IDictionaryService {

    @Autowired
    private RedisComp redisComp;

    @Override
    public List<BasicDictionary> queryTree(String dataType) {
        return lambdaQuery().like(StringUtil.isNotEmpty(dataType), BasicDictionary::getDataType, dataType).list();
    }

    @Override
    public Page<BasicDictionary> queryPage(DictionaryQuery form) {
        BasicDictionary data = new BasicDictionary();
        BeanUtils.copyProperties(form, data);
        data.setDataStatus(BaseConstants.STATUS_0);
        data.setIsDel(BaseConstants.STATUS_0);
        return lambdaQuery().setEntity(data).page(new Page<>(form.getCurrent(), form.getSize()));
    }

    @Override
    public BasicDictionary getBean(String dataType, String dataCode) {
        if (StringUtil.isOneEmpty(dataType, dataCode)) {
            BaseException.throwException("参数为空");
        }
        return lambdaQuery().eq(BasicDictionary::getDataType, dataCode).eq(BasicDictionary::getDataCode, dataCode).one();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void insert(BasicDictionary mod) {
        Objects.requireNonNull(mod, "参数为空");
        // 非空校验
        String msg = checkMain(mod);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        // 校验重复
        BasicDictionary check = lambdaQuery().eq(BasicDictionary::getDataType, mod.getDataType()).eq(BasicDictionary::getDataCode, mod.getDataCode()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s/%s]已存在", mod.getDataType(), mod.getDataCode()));
        }
        // 保存
        boolean success = true;
        try {
            Date date = new Date();
            mod.setCreateTime(date);
            mod.setLastUpdateTime(date);
            mod.setDataStatus(BaseConstants.STATUS_0);
            mod.setIsDel(BaseConstants.STATUS_0);
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
    public void update(BasicDictionary mod) {
        Objects.requireNonNull(mod, "参数为空");
        // 非空校验
        String msg = checkMain(mod);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        BasicDictionary old = lambdaQuery().eq(BasicDictionary::getDataType, mod.getDataType()).eq(BasicDictionary::getDataCode, mod.getDataCode()).one();
        Objects.requireNonNull(old, "原信息不存在");
        // 校验重复
        BasicDictionary check = lambdaQuery().eq(BasicDictionary::getDataType, mod.getDataType()).eq(BasicDictionary::getDataCode, mod.getDataCode()).ne(BasicDictionary::getId, old.getId()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s/%s]已存在", mod.getDataType(), mod.getDataCode()));
        }
        // 更新物资
        boolean success = true;
        try {
            mod.setId(old.getId());
            mod.setDataType(old.getDataType());
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
    public void delete(String dataType, String dataCode) {
        if (StringUtil.isOneEmpty(dataType, dataCode)) {
            BaseException.throwException("参数为空");
        }
        // 删除企业
        boolean success = true;
        BasicDictionary old = lambdaQuery().eq(BasicDictionary::getDataType, dataType).eq(BasicDictionary::getDataCode, dataCode).one();
        Objects.requireNonNull(old, "原信息不存在");
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

    @Override
    public void loadCache() {
        List<BasicDictionary> list = lambdaQuery().eq(BasicDictionary::getDataStatus, BaseConstants.STATUS_0).eq(BasicDictionary::getIsDel, BaseConstants.STATUS_0).list();
        for (BasicDictionary mod : list) {
            redisComp.lSet(BillEnum.BasicDictionary.getRedisKey() + mod.getDataType(), mod);
        }
    }

    private String checkMain(BasicDictionary mod) {
        String msg = "";
        msg += StringUtil.emptyToMsg(mod.getDataType(), "类型为空");
        msg += StringUtil.emptyToMsg(mod.getDataCode(), "编码为空");
        msg += StringUtil.emptyToMsg(mod.getDataValue(), "值为空");

        if (StringUtil.isNotEmpty(msg) && msg.endsWith(BaseConstants.SPLIT_CHARACTER)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

}
