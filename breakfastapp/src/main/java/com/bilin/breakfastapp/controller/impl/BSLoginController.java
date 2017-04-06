package com.bilin.breakfastapp.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bilin.breakfastapp.controller.LoginController;
import com.bilin.breakfastapp.exceptions.ServiceException;
import com.bilin.breakfastapp.service.LoginService;
import com.bilin.breakfastapp.vo.User;

/**
 * 
 */

@Controller
@SessionAttributes("user")
public class BSLoginController implements LoginController {

    /**
     * Default constructor
     */
    public BSLoginController() {
    }

    @Autowired
    private LoginService loginService;
    /**
     * 
     */
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam("username") String userId, @RequestParam("password") String password, ModelMap model) {
        String view = "login";
        System.out.println(userId + ":" + password);
        User user = null;
        try {
            user = loginService.authenticateUser(userId, password);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(user == null) {
            model.addAttribute("errorMsg", "Invalid Credentials!!!");
            view = "login";
        } else if(user.isAdmin()){
            model.addAttribute("user", user);
            view = "adminHome";
        } else {
            model.addAttribute("user", user);
            view = "index";
        }
        return view;
               
    }

    /**
     * 
     */
    public String register() {
        // TODO implement here
        return null;
    }

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }

    @Override
    public String login() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String logout() {
        // TODO Auto-generated method stub
        return null;
    }
}