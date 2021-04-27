package com.sid.xk.shake.controller;

import com.sid.xk.shake.error.service.ILogErrorService;
import com.sid.xk.shake.info.service.ILogInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制器
 * @author wuxiaodong
 * @date 2021/03/28
 */
public class BaseController {
    @Autowired
    private ILogInfoService logInfoService;
    @Autowired
    private ILogErrorService logErrorService;

    protected void errorLog(Exception e) {

    }

    protected void infoLog() {

    }

}
