package cn.mkh.blog.web.service;

import cn.mkh.blog.web.domain.Tag;

import java.util.List;

public interface TagsService {
    public List<Tag> findAllTags(Integer pageNum, Integer pageSize);

    Tag findByName(Tag tag);

    Integer addTag(Tag tag);

    void delTag(Integer id);

    Tag findById(Integer id);

    Integer updateTag(Tag tag);

    List<Tag> findTagsTop(int i);
}
