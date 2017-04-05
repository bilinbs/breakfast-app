package com.bilin.breakfastapp.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bilin.breakfastapp.controller.LoginController;
import com.bilin.breakfastapp.service.LoginService;

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
        System.out.println(userId + ":" + password);
        model.addAttribute("errorMsg", "Invalid Credentials!!!");
        return "login";
               
    }

    /**
     * 
     */
    public String logout() {
        // TODO implement here
        return null;
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
}