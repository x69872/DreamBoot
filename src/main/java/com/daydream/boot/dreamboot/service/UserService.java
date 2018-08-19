package com.daydream.boot.dreamboot.service;

import com.daydream.boot.dreamboot.dao.UserMapper;
import com.daydream.boot.dreamboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author GaoJian
 * @date 2017/12/17/0017
 */
@Service
@Slf4j
//@CacheConfig(cacheNames = "user")
@Transactional(rollbackFor = Exception.class)
public class UserService
{
    @Autowired
    private UserMapper userMapper;

    //    @Cacheable(cacheNames = "selectAll")
    public List<User> selectAll()
    {
        return userMapper.selectByExample(null);
    }


    public User getUser(long objectid)
    {
        return userMapper.selectByPrimaryKey(objectid);
    }

    @Transactional(rollbackFor = DataAccessException.class)

    public Integer addUser(User user)
    {
        return userMapper.insert(user);
    }

    @Transactional(rollbackFor = DataAccessException.class)

    public Integer modifyUser(User user)
    {
        return userMapper.updateByPrimaryKey(user);
    }

    @Transactional(rollbackFor = DataAccessException.class)

    public Integer deleteUser(long objectid)
    {
        return userMapper.deleteByPrimaryKey(objectid);
    }
}
