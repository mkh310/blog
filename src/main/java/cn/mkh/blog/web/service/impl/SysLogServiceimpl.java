package cn.mkh.blog.web.service.impl;

import cn.mkh.blog.web.domain.SysLog;
import cn.mkh.blog.web.dao.SysLogDao;
import cn.mkh.blog.web.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceimpl implements SysLogService {

    @Autowired
    private SysLogDao dao;

    @Override
    public void save(SysLog sysLog) {
        dao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return dao.findAll();
    }
}
