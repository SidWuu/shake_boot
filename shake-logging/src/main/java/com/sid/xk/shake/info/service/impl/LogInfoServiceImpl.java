package com.sid.xk.shake.info.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sid.xk.shake.info.entity.SystemLogInfo;
import com.sid.xk.shake.info.mapper.LogInfoMapper;
import com.sid.xk.shake.info.service.ILogInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author wuxiaodong
 * @since 2021-04-18
 */
@Service
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, SystemLogInfo> implements ILogInfoService {

}
