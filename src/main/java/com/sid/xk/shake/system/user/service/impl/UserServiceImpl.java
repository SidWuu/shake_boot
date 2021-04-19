package com.sid.xk.shake.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.common.constants.BaseConstants;
import com.sid.xk.shake.common.exception.BaseException;
import com.sid.xk.shake.common.utils.StringUtil;
import com.sid.xk.shake.system.login.utils.PassUtil;
import com.sid.xk.shake.system.user.entity.SystemUser;
import com.sid.xk.shake.system.user.mapper.UserMapper;
import com.sid.xk.shake.system.user.service.IUserService;
import com.sid.xk.shake.system.user.vo.UserQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements IUserService {

    @Override
    public Page<SystemUser> queryPage(UserQuery form) {
        SystemUser data = new SystemUser();
        BeanUtils.copyProperties(form, data);
        data.setUserStatus(BaseConstants.STATUS_0);
        data.setIsDel(BaseConstants.STATUS_0);
        QueryWrapper<SystemUser> query = new QueryWrapper<SystemUser>().setEntity(data);
        Page<SystemUser> page = new Page<>(form.getCurrent(), form.getSize());
        return page(page, query);
    }

    @Override
    public SystemUser getBean(String userName) {
        BaseException.requireNonNull(userName, "用户名为空");
        return lambdaQuery().eq(SystemUser::getUserName, userName).one();
    }

    @Override
    public void insert(SystemUser mod) {
        BaseException.requireNonNull(mod, "参数为空");
        // 默认值
        setDefault(mod);
        // 校验物资
        String msg = checkMain(mod);
        if (StringUtil.isNotEmpty(msg)) {
            BaseException.throwException(msg);
        }
        // 校验重复
        SystemUser check = lambdaQuery().eq(SystemUser::getUserName, mod.getUserName()).one();
        if (null != check) {
            BaseException.throwException(String.format("[%s]已存在", mod.getUserName()));
        }
        // 保存
        boolean success = true;
        try {
            // 密码加盐
            String salt = StringUtil.randomString(8);
            mod.setSalt(salt);
            String password = PassUtil.encrypt(mod.getUserName(), mod.getUserPassword(), mod.getSalt());
            mod.setUserPassword(password);
            success = save(mod);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("保存失败");
        }
    }

    @Override
    public void update(SystemUser mod) {
        BaseException.requireNonNull(mod, "参数为空");
        SystemUser old = lambdaQuery().eq(SystemUser::getUserName, mod.getUserName()).one();
        BaseException.requireNonNull(old, "用户信息不存在");
        // 更新
        boolean success = true;
        try {
            mod.setId(old.getId());
            mod.setUserName(old.getUserName());
            mod.setSalt(old.getSalt());
            mod.setUserPassword(old.getUserPassword());
            mod.setLastUpdateTime(LocalDateTime.now());
            success = updateById(mod);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("更新失败");
        }
    }

    @Override
    public void delete(String userName) {
        BaseException.requireNonNull(userName, "参数为空");
        // 删除企业
        boolean success = true;
        SystemUser old = lambdaQuery().eq(SystemUser::getUserName, userName).one();
        BaseException.requireNonNull(old, "用户信息不存在");
        if (StringUtil.equals(BaseConstants.STATUS_1, old.getIsDel())) {
            BaseException.throwException("已删除");
        }
        try {
            old.setIsDel(BaseConstants.STATUS_1);
            old.setLastUpdateTime(LocalDateTime.now());
            success = updateById(old);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("删除失败");
        }
    }

    @Override
    public void reset(String userName, String oldPassword, String newPassword) {
        if (StringUtil.isOneEmpty(userName, oldPassword, newPassword)) {
            BaseException.throwException("参数为空");
        }
        SystemUser old = lambdaQuery().eq(SystemUser::getUserName, userName).one();
        BaseException.requireNonNull(old, "用户信息不存在");

        String oldPasswordEncode = PassUtil.encrypt(userName, oldPassword, old.getSalt());
        if (!StringUtil.equals(oldPasswordEncode, old.getUserPassword())) {
            BaseException.throwException("原密码错误");
        }
        String newPasswordEncode = PassUtil.encrypt(userName, newPassword, old.getSalt());
        if (StringUtil.equals(newPasswordEncode, old.getUserPassword())) {
            BaseException.throwException("新密码与原密码相同");
        }
        boolean success = true;
        try {
            old.setUserPassword(newPasswordEncode);
            old.setLastUpdateTime(LocalDateTime.now());
            success = updateById(old);
        } catch (RuntimeException e) {
            BaseException.throwException(e.getMessage());
        }
        if (!success) {
            BaseException.throwException("更新失败");
        }
    }

    private void setDefault(SystemUser mod) {
        LocalDateTime date = LocalDateTime.now();
        mod.setCreateTime(date);
        mod.setLastUpdateTime(date);
        mod.setUserStatus(BaseConstants.STATUS_0);
        mod.setIsDel(BaseConstants.STATUS_0);
    }

    private String checkMain(SystemUser mod) {
        String msg = "";
        msg += StringUtil.emptyToMsg(mod.getUserName(), "用户名为空");
        msg += StringUtil.emptyToMsg(mod.getUserPassword(), "密码为空");
        msg += StringUtil.emptyToMsg(mod.getUserType(), "品名为空");

        if (StringUtil.isNotEmpty(msg) && msg.endsWith(BaseConstants.SPLIT_CHARACTER)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }

}
