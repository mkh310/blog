package cn.mkh.blog.web.service.impl;

import cn.mkh.blog.web.dao.TypeDao;
import cn.mkh.blog.web.domain.Type;
import cn.mkh.blog.web.service.TypesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<Type> findAllTypes(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return typeDao.findAll();
    }

    @Override
    public Integer addType(Type type) {
        typeDao.addType(type);
        return type.getId();
    }

    @Override
    public Type findByName(Type type) {
        return  typeDao.findByName(type);
    }

    @Override
    public void delType(Integer id) {
        typeDao.delType(id);
    }

    @Override
    public Integer updateType(Type type) {
        return typeDao.updaType(type);
    }

    @Override
    public Type findById(Integer id) {
        return typeDao.findById(id);
    }

    @Override
    public List<Type> findTypeTop(Integer i) {
        return typeDao.findTypeTop(i);
    }
}
