package cn.mkh.blog.web.service.impl;

import cn.mkh.blog.util.MD5Util;
import cn.mkh.blog.web.dao.UserDao;
import cn.mkh.blog.web.domain.User;
import cn.mkh.blog.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    @Override
    public User checkUser(String username, String password) {
        return dao.findUserByPasswordAndName(username,MD5Util.code(password));
    }
}
