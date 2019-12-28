package com.rookie.service;

import com.rookie.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
