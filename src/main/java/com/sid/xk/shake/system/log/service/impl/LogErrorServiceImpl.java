package com.sid.xk.shake.system.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.system.log.entity.SystemLogError;
import com.sid.xk.shake.system.log.mapper.LogErrorMapper;
import com.sid.xk.shake.system.log.service.ILogErrorService;
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
