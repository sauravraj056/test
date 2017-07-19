package com.controller;

import com.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by root on 18/7/17.
 */

@Controller
public class UserLoginController {

    @Autowired
    UserServiceImpl userService;


    @RequestMapping(value = "/Login" , method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password)
    {
        ModelAndView modelAndView = null;

        boolean check = userService.userLogin(username,password);
        if(check == true)
        {
            modelAndView = new ModelAndView("DashBoard");
            modelAndView.addObject("username",username);
        }
        else
        {
            modelAndView = new ModelAndView("Home_Page");
        }
//        System.out.println(username);
        return modelAndView;
    }

    @RequestMapping(value = "/Logout")
    public ModelAndView logout(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView("Home_Page");
        HttpSession session = request.getSession(false);
        session.invalidate();
        return modelAndView;
    }

    @RequestMapping(value = "/checkusername")
    public String checkUsernameValidity(@RequestParam(value = "data") String userdata)
    {
        System.out.println(userdata);
        return userdata;
    }
}
