package com.daydream.boot.Dreamboot.service;

import com.daydream.boot.Dreamboot.mapper.UserMapper;
import com.daydream.boot.Dreamboot.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GaoJian on 2017/12/17/0017.
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserService
{
    @Autowired
    UserMapper mapper;

    @Cacheable(cacheNames = "selectAll")
    public List<User> selectAll()
    {

        return mapper.selectByExample(null);
    }

}
