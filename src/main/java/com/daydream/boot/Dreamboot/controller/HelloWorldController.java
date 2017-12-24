package com.daydream.boot.Dreamboot.controller;

import com.daydream.boot.Dreamboot.model.DemoConfig;
import com.daydream.boot.Dreamboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author GaoJian
 */
@RestController
@RequestMapping("/springboot")
public class HelloWorldController
{
    @Autowired
    User user;
    @Autowired
    DemoConfig config;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sayWorld(@PathVariable("name") String name)
    {
        System.out.println("userName:" + user.getName());
        System.out.println("userName:" + config.getServers());
        return "Hello " + name;

    }

}