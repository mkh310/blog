package cn.mkh.blog.web.dao;

import cn.mkh.blog.web.domain.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao {
    @Select("select * from type")
    public List<Type> findAll();

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into type(name) values(#{name})")
    int addType(Type type);

    @Select("select * from type where name = #{name}")
    Type findByName(Type type);

    @Delete("delete from type where id = #{id}")
    void delType(Integer id);

    @Update("update type set name = #{name} where id = #{id}")
    Integer updaType(Type type);

    @Select("select * from type where id = #{id}")
    Type findById(Integer id);

    @Select("SELECT " +
            "t.`id`,t.`name`,IFNULL(COUNT(b.id),0)countBlog " +
            "FROM " +
            "type t " +
            "LEFT JOIN " +
            "blog b " +
            "ON " +
            "t.id = b.t_type " +
            "GROUP BY " +
            "t.`id` " +
            "ORDER BY  countBlog DESC " +
            "LIMIT 0,#{i};")
    List<Type> findTypeTop(Integer i);
}
