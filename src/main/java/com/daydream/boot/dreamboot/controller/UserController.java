package com.daydream.boot.dreamboot.controller;


import com.daydream.boot.dreamboot.entity.User;
import com.daydream.boot.dreamboot.service.UserService;
import com.daydream.boot.dreamboot.utils.CommonResponse;
import com.daydream.boot.dreamboot.utils.ResponseUtil;
import com.daydream.boot.dreamboot.utils.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;


/**
 * @author GaoJian
 * @date 2017/12/17/0017
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController
{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }


    /**
     * 查询用户列表
     *
     * @return List<User>
     */
    @GetMapping
    public List<User> showUsers()
    {
        return userService.selectAll();
    }

    @GetMapping("/{id}")
    public CommonResponse getUser(@PathVariable("id") Long objectid) throws ServiceException
    {
        return ResponseUtil.generateResponse(userService.getUser(objectid));
    }

    @PutMapping("/{id}")
    public CommonResponse updateUser(@RequestBody User user) throws ServiceException
    {
        return ResponseUtil.generateResponse(userService.modifyUser(user));
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteUser(@PathVariable("id") long objectid) throws ServiceException
    {
        return ResponseUtil.generateResponse(userService.deleteUser(objectid));
    }

    @PostMapping
    public CommonResponse addUser(@RequestBody User user) throws ServiceException
    {
        for (long i = 1; 8000000 > i; i++)
        {
            user.setObjectid(i);
            user.setTelephone(i + 12345678923L);
            userService.addUser(user);
            log.info("===========add " + i + " user to db==========" + user.toString());
        }

        return ResponseUtil.generateResponse(userService.addUser(user));
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
