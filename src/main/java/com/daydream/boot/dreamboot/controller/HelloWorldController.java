package com.daydream.boot.dreamboot.controller;

import com.daydream.boot.dreamboot.service.TimerTestService;
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


    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String sayWorld(@PathVariable("name") String name)
    {
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