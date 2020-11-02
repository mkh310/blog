package cn.mkh.blog.web.dao;


import cn.mkh.blog.web.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogDao {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method,ExceptionMessage) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method},#{ExceptionMessage})")
    void save(SysLog sysLog);


    @Select("select * from syslog")
    List<SysLog> findAll();
}
