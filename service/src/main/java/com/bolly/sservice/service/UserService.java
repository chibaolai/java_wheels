package com.bolly.sservice.service;


import com.bolly.sservice.entity.User;

import java.util.List;

public interface UserService {
    void insert(User user);

    User get(long id);

    List<User> getList(User cond);

    int queryForInt();
}
