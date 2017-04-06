package com.bilin.breakfastapp.controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/logout")
public class BSLogoutController {
    @RequestMapping(method=RequestMethod.GET)
    public String logout(HttpServletRequest request) {
      request.getSession().invalidate();
      return "index";
    }
}
