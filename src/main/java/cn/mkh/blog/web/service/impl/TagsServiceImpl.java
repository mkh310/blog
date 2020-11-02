package cn.mkh.blog.web.service.impl;

import cn.mkh.blog.web.dao.TagDao;
import cn.mkh.blog.web.domain.Tag;
import cn.mkh.blog.web.service.TagsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> findAllTags(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return tagDao.findAll();
    }

    @Override
    public Integer addTag(Tag tag) {
        tagDao.addTag(tag);
        return tag.getId();
    }

    @Override
    public Tag findByName(Tag tag) {
        return  tagDao.findByName(tag);
    }

    @Override
    public void delTag(Integer id) {
        tagDao.delTag(id);
    }

    @Override
    public Integer updateTag(Tag tag) {
        return tagDao.updaTag(tag);
    }

    @Override
    public List<Tag> findTagsTop(int i) {
            
        return tagDao.findTagsTop(i);
    }

    @Override
    public Tag findById(Integer id) {
        return tagDao.findById(id);
    }
}
