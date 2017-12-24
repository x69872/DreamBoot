package com.daydream.boot.Dreamboot.controller;


import com.daydream.boot.Dreamboot.pojo.User;
import com.daydream.boot.Dreamboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;


/**
 * Created by GaoJian on 2017/12/17/0017.
 */
@RestController
public class UserController
{

    @Autowired
    UserService userService;


    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping(value = "/showUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> showUsers()
    {

        return userService.selectAll();

    }

    @RequestMapping(value = "/uid", method = RequestMethod.GET)
    String uid(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null)
        {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

}
