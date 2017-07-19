package com.controller;

import com.model.User;
import com.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Created by root on 13/7/17.
 */


@Controller
public class UserRegistrationController {

    //    @Autowired
//    UserDao userDao;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView start_loginpage() {
        ModelAndView modelAndView = new ModelAndView("Home_Page");
        return modelAndView;
    }

    /*@RequestMapping(value = "/checkusername", method = RequestMethod.GET)
    public ModelAndView chkusername() {
        ModelAndView modelAndView = new ModelAndView("Home_Page");
        return modelAndView;
    }*/
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute User user, @RequestParam("file") MultipartFile[] fileupload, HttpServletRequest request) throws IOException, ServletException {




        int counter = 0;
        boolean check;
        ModelAndView modelAndView = null;

        try {

        modelAndView = new ModelAndView();
        modelAndView.setViewName("Home_Page");
        
        if (user.getFname().compareTo("") == 0){
            modelAndView.addObject("firstname_msg","*empty field"); 
            counter++;
        }
        if (user.getLname().compareTo("") == 0){
            modelAndView.addObject("lastname_msg","*empty field");
            counter++;
        }
        if (user.getUsername().compareTo("") == 0){
            modelAndView.addObject("username_msg","*empty field");
            counter++;
        }
        if (user.getEmail().compareTo("") == 0){
            modelAndView.addObject("email_msg","*empty field");
            counter++;
        }
        if (user.getPassword().compareTo("") == 0){
            modelAndView.addObject("password_msg","*empty field");
            counter++;
        }
       else if(request.getParameter("confirm_password").compareTo("") == 0)
        {
            modelAndView.addObject("confirmpassword_msg","*empty field");
            counter++;
        }
        else{

            int compare = user.getPassword().compareTo(request.getParameter("confirm_password"));

            if(compare != 0) {
                modelAndView.addObject("password_mismatch_msg", "*password mismatch");
                counter++;
            }
        }





            if (counter == 0) {


                userService.userImageUpload(fileupload, user);

                modelAndView = new ModelAndView();
                modelAndView.setViewName("DashBoard");

//            System.out.println(user.toString());

                check = userService.addUser(user);


                if (check == true) {
                    modelAndView.addObject("username", user.getUsername());
                }
                else
                {
                    modelAndView.setViewName("Home_Page");
                    modelAndView.addObject("username","invalid");
                    modelAndView.addObject("email_msg","invalid");
            }
        }
        }
        catch (Exception e)
        {
            System.out.println(e);

        }
        return modelAndView;


    }
}
