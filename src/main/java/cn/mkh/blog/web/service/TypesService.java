package cn.mkh.blog.web.service;

import cn.mkh.blog.web.domain.Type;

import java.util.List;

public interface TypesService {
    public List<Type> findAllTypes(Integer pageNum, Integer pageSize);

    Integer addType(Type type);

    Type findByName(Type type);

    void delType(Integer id);

    Integer updateType(Type type);

    Type findById(Integer id);

    List<Type> findTypeTop(Integer i);

}
