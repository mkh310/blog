package cn.mkh.blog.web.service;

import cn.mkh.blog.web.domain.SysLog;

import java.util.List;

public interface SysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll();
}
