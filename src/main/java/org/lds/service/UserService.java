package org.lds.service;

import org.lds.dao.UserDao;
import org.lds.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getByIdService(int id)
    {
        return userDao.getById(id);
    }
}
