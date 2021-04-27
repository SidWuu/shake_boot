package com.sid.xk.shake.system.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sid.xk.shake.system.user.entity.SystemUser;
import com.sid.xk.shake.system.user.vo.UserQuery;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-18
 */
public interface IUserService extends IService<SystemUser> {

    /**
     * 分页查询
     * @param form 查询条件
     * @return Page<Company>
     */
    Page<SystemUser> queryPage(UserQuery form);

    /**
     * 查询详情
     * @param userName 用户名
     * @return SystemUser
     */
    SystemUser getBean(String userName);

    /**
     * 新增
     * @param mod 保存bean
     */
    void insert(SystemUser mod);

    /**
     * 修改
     * @param mod 保存bean
     */
    void update(SystemUser mod);

    /**
     * 删除
     * @param userName 用户名
     */
    void delete(String userName);

    /**
     * 重置密码
     * @param userName 用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    void reset(String userName, String oldPassword, String newPassword);

}
