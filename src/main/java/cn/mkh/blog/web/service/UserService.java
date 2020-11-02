package cn.mkh.blog.web.service;

import cn.mkh.blog.web.domain.User;

public interface UserService {
    User checkUser(String username, String password);
}
