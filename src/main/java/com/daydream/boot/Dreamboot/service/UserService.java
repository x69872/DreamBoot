package com.daydream.boot.Dreamboot.service;

import com.daydream.boot.Dreamboot.dao.UserMapper;
import com.daydream.boot.Dreamboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by GaoJian on 2017/12/17/0017.
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

}
