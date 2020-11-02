package cn.mkh.blog.web.dao;

import cn.mkh.blog.web.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from user where password = #{password} and username = #{username}")
    public User findUserByPasswordAndName(String username,String password);

    @Select("select * from user where id = #{id}")
    public User findUserById(Integer id);
}
