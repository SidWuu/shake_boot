package com.sid.xk.shake.system.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sid.xk.shake.constants.BaseConstants;
import com.sid.xk.shake.constants.StatusEnum;
import com.sid.xk.shake.controller.BaseController;
import com.sid.xk.shake.system.user.entity.SystemUser;
import com.sid.xk.shake.system.user.service.IUserService;
import com.sid.xk.shake.system.user.vo.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-18
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping("/pages")
    public ModelMap query(@RequestBody UserQuery form) {
        ModelMap modelMap = new ModelMap();
        try {
            Page<SystemUser> data = userService.queryPage(form);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @GetMapping("/edit/{userName}")
    public ModelMap edit(@PathVariable String userName) {
        ModelMap modelMap = new ModelMap();
        try {
            SystemUser data = userService.getBean(userName);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, data);
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PostMapping("/save")
    public ModelMap save(@RequestBody SystemUser mod) {
        ModelMap modelMap = new ModelMap();
        try {
            userService.insert(mod);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @PutMapping("/update")
    public ModelMap update(@RequestBody SystemUser mod) {
        ModelMap modelMap = new ModelMap();
        try {
            userService.update(mod);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    @DeleteMapping("/delete/{userName}")
    public ModelMap delete(@PathVariable String userName) {
        ModelMap modelMap = new ModelMap();
        try {
            userService.delete(userName);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

    /** 主动修改密码, 忘记密码功能延后 */
    @DeleteMapping("/reset")
    public ModelMap reset(@PathVariable String userName, @PathVariable String oldPassword, @PathVariable String newPassword) {
        ModelMap modelMap = new ModelMap();
        try {
            userService.reset(userName, oldPassword, newPassword);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.SUCCESS.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.ERROR.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_MESSAGE, StatusEnum.ERROR.getMsg());
        }
        return modelMap;
    }

}
