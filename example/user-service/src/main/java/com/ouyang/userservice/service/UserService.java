package com.ouyang.userservice.service;

import com.ouyang.userservice.entity.User;

import java.util.List;

/**
 * Created by ouyangjiajun <ouyangjiajun@qq.com> on 2019/8/29.
 */
public interface UserService {
    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getByUsername(String username);

    List<User> getUserByIds(List<Long> ids);
}
