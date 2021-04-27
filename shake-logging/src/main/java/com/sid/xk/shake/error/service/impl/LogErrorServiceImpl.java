package com.sid.xk.shake.error.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.error.entity.SystemLogError;
import com.sid.xk.shake.error.mapper.LogErrorMapper;
import com.sid.xk.shake.error.service.ILogErrorService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 异常日志表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-18
 */
@Service
public class LogErrorServiceImpl extends ServiceImpl<LogErrorMapper, SystemLogError> implements ILogErrorService {

}
