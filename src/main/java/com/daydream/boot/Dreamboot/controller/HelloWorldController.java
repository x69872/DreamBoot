package com.daydream.boot.Dreamboot.controller;

import com.daydream.boot.Dreamboot.model.DemoConfig;
import com.daydream.boot.Dreamboot.model.User;
import com.daydream.boot.Dreamboot.service.TimerTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author GaoJian
 */
@RestController
@RequestMapping("/springboot")
public class HelloWorldController
{
    @Autowired
    TimerTestService timerTestService;
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

    @RequestMapping(value = "/timer", method = RequestMethod.GET)
    public String timerTask(HttpServletRequest request)
    {
        timerTestService.timerTask1();
        timerTestService.timerTask2();

        return null;
    }
}