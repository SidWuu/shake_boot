package com.sid.xk.shake.system.login.controller;

import com.sid.xk.shake.component.RedisComp;
import com.sid.xk.shake.constants.BaseConstants;
import com.sid.xk.shake.constants.StatusEnum;
import com.sid.xk.shake.controller.BaseController;
import com.sid.xk.shake.exception.BaseException;
import com.sid.xk.shake.system.login.utils.JwtUtil;
import com.sid.xk.shake.system.login.utils.PassUtil;
import com.sid.xk.shake.system.login.utils.RandImageUtil;
import com.sid.xk.shake.system.login.vo.LoginResult;
import com.sid.xk.shake.system.login.vo.SystemLogin;
import com.sid.xk.shake.system.user.entity.SystemUser;
import com.sid.xk.shake.system.user.service.IUserService;
import com.sid.xk.shake.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author wuxiaodong
 * @date 2021/04/18
 */
@RestController
@RequestMapping("/system")
public class LoginController extends BaseController {

    @Autowired
    private RedisComp redisComp;
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ModelMap login(@RequestBody SystemLogin form) {
        ModelMap modelMap = new ModelMap();
        try {
            // 非空校验
            BaseException.requireNonNull(form, "参数为空");
            BaseException.requireNonNull(form.getUsername(), "用户名为空");
            BaseException.requireNonNull(form.getPassword(), "密码为空");
            BaseException.requireNonNull(form.getCaptcha(), "验证码为空");
            BaseException.requireNonNull(form.getCheckKey(), "验证码为空");
            // 校验验证码
            String lowerCaseCaptcha = form.getCaptcha().toLowerCase();
            String realKey = realKey(lowerCaseCaptcha, form.getCheckKey());
            Object checkCode = redisComp.get(realKey);
            if (null == checkCode || !StringUtil.equals(lowerCaseCaptcha, checkCode)) {
                BaseException.throwException("验证码错误");
            }
            // 校验用户
            SystemUser user = userService.getBean(form.getUsername());
            BaseException.requireNonNull(user, String.format("用户[%s]不存在", form.getUsername()));
            if (StringUtil.equals(BaseConstants.STATUS_1, user.getIsDel())) {
                BaseException.throwException(String.format("用户[%s]不存在", form.getUsername()));
            }
            if (StringUtil.equals(BaseConstants.STATUS_1, user.getUserStatus())) {
                BaseException.throwException(String.format("用户[%s]未启用", form.getUsername()));
            }
            // 校验密码
            String passwordEncode = PassUtil.encrypt(form.getUsername(), form.getPassword(), user.getSalt());
            if (!StringUtil.equals(passwordEncode, user.getUserPassword())) {
                BaseException.throwException("用户名或密码错误");
            }
            // 缓存用户信息
            LoginResult result = userInfo(user);
            // 登录成功删除缓存验证码key
            redisComp.del(realKey);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, result);
        } catch (BaseException e) {
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

    @GetMapping("/logout")
    public ModelMap logout(HttpServletRequest request) {
        ModelMap modelMap = new ModelMap();
        try {
            String token = request.getHeader(BaseConstants.X_ACCESS_TOKEN);
            BaseException.requireNonNull(token, "退出失败");
            String username = JwtUtil.getUsername(token);
            SystemUser user = userService.getBean(username);
            BaseException.requireNonNull(user, "token无效");
            // 清空token
            redisComp.del(BaseConstants.PREFIX_USER_TOKEN + token);
            // NEXT 清空权限缓存

            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
        } catch (BaseException e) {
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

    @GetMapping("/randomImage/{key}")
    public ModelMap randomImage(@PathVariable String key) {
        ModelMap modelMap = new ModelMap();
        try {
            String code = StringUtil.randomString(StringUtil.BASE_RANDOM_FULL_CHAR_NUMBER, 6);
            String lowerCaseCode = code.toLowerCase();
            redisComp.set(realKey(lowerCaseCode, key), lowerCaseCode, 60);
            String base64 = RandImageUtil.generate(code);
            modelMap.addAttribute(BaseConstants.RES_RETURN_STATUS, StatusEnum.SUCCESS.getStatus());
            modelMap.addAttribute(BaseConstants.RES_RETURN_DATA, base64);
        } catch (BaseException e) {
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

    private String realKey(String captcha, String checkKey) throws UnsupportedEncodingException {
        return StringUtil.getMd5(captcha + checkKey, "utf-8");
    }


    /**
     * 封装用户信息
     * @param user
     * @return
     */
    private LoginResult userInfo(SystemUser user) {
        // 生成 token
        String token = JwtUtil.sign(user.getUserName(), user.getUserPassword());

        redisComp.set(BaseConstants.PREFIX_USER_TOKEN + token, token, JwtUtil.EXPIRE_TIME * 2 / 1000);
        // NEXT 权限暂缓

        LoginResult result = new LoginResult();
        result.setToken(token);
        result.setUser(user);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }
}
